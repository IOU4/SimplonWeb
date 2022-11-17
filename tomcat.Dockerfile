FROM tomcat:10.1-jre17 as base

WORKDIR /usr/local/tomcat/

# download postgresql jdbc
RUN ["curl", "https://jdbc.postgresql.org/download/postgresql-42.5.0.jar", "--output", "./lib/postgresql-42.5.0.jar" ]

# download jakarta jstl 
RUN ["curl", "https://repo1.maven.org/maven2/jakarta/servlet/jsp/jstl/jakarta.servlet.jsp.jstl-api/2.0.0/jakarta.servlet.jsp.jstl-api-2.0.0.jar", "--output", "lib/jakarta.servlet.jsp.jstl-api-2.0.0.jar"]
RUN ["curl", "https://repo.maven.apache.org/maven2/org/glassfish/web/jakarta.servlet.jsp.jstl/2.0.0/jakarta.servlet.jsp.jstl-2.0.0.jar", "--output", "lib/jakarta.servlet.jsp.jstl-2.0.0.jar"]

# download jakarta mail
RUN ["curl", "https://repo.maven.apache.org/maven2/org/eclipse/angus/angus-core/1.0.0/angus-core-1.0.0.jar", "--output", "lib/angus-core-1.0.0.jar"]
RUN ["curl", "https://repo1.maven.org/maven2/jakarta/mail/jakarta.mail-api/2.1.0/jakarta.mail-api-2.1.0.jar", "--output", "lib/jakarta.mail-api-2.1.0.jar"]
RUN ["curl", "https://repo1.maven.org/maven2/org/eclipse/angus/angus-activation/1.0.0/angus-activation-1.0.0.jar", "--output", "lib/angus-activation-1.0.0.jar"]
RUN ["curl", "https://repo1.maven.org/maven2/jakarta/activation/jakarta.activation-api/2.1.0/jakarta.activation-api-2.1.0.jar", "--output", "lib/jakarta.activation-api-2.1.0.jar"]
RUN ["curl", "https://repo1.maven.org/maven2/org/eclipse/angus/jakarta.mail/1.0.0/jakarta.mail-1.0.0.jar", "--output", "lib/jakarta.mail-1.0.0.jar"]

# download jakarta persistence and hibernate
RUN ["curl", "https://repo1.maven.org/maven2/jakarta/persistence/jakarta.persistence-api/3.0.0/jakarta.persistence-api-3.0.0.jar", "--output", "lib/jakarta.persistence-api-3.0.0.jar"]
RUN ["curl", "https://repo1.maven.org/maven2/org/hibernate/hibernate-core/5.6.0.Final/hibernate-core-jakarta-5.6.0.Final.jar", "--output", "lib/hibernate-core-jakarta-5.6.0.Final.jar"]
RUN ["curl", "https://repo1.maven.org/maven2/org/hibernate/hibernate-entitymanager/5.6.0.Final/hibernate-entitymanager-5.6.0.Final.jar", "--output", "lib/hibernate-entitymanager-5.6.0.Final.jar"]

FROM base as dev
# enable tomcat-manager for reloading the app in dev
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
RUN rm -rf webapps.dist/
COPY build/libs/SimplonClone.war webapps/ROOT.war
