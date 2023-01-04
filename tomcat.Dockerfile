FROM tomcat:10.1-jre17 as base

WORKDIR /usr/local/tomcat/

# download postgresql jdbc
RUN ["curl", "https://jdbc.postgresql.org/download/postgresql-42.5.0.jar", "--output", "./lib/postgresql-42.5.0.jar" ]

FROM base as dev

# enable tomcat-manager for faster reloading 
RUN ["cp", "-r", "webapps.dist/manager", "webapps/"]
RUN echo '\
  <?xml version="1.0" encoding="UTF-8"?>\
  <Context antiResourceLocking="false" privileged="true" >\
  <CookieProcessor className="org.apache.tomcat.util.http.Rfc6265CookieProcessor"\
  sameSiteCookies="strict" />\
  <Manager sessionAttributeValueClassNameFilter="java\.lang\.(?:Boolean|Integer|Long|Number|String)|org\.apache\.catalina\.filters\.CsrfPreventionFilter\$LruCache(?:\$1)?|java\.util\.(?:Linked)?HashMap"/>\
  </Context>\
  ' > webapps/manager/META-INF/context.xml
RUN echo '\
  <tomcat-users xmlns="http://tomcat.apache.org/xml"\
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"\
  xsi:schemaLocation="http://tomcat.apache.org/xml tomcat-users.xsd"\
  version="1.0">\
  <role rolename="manager-script"/>\
  <user username="tomcat" password="secret" roles="manager-script"/>\
  </tomcat-users> ' > conf/tomcat-users.xml

FROM base as prod
RUN rm -rf webapps.dist/ webapps/
COPY build/libs/SimplonClone.war webapps/ROOT.war
