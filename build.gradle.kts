plugins {
  java
  war
}

repositories { 
  mavenCentral()
}
// define jdk version
java { sourceCompatibility = JavaVersion.VERSION_17 }

dependencies { 
    // servlet
    implementation("jakarta.servlet:jakarta.servlet-api:5.0.0")
    implementation("org.glassfish.web:jakarta.servlet.jsp.jstl:2.0.0")

    // mail
    implementation("jakarta.mail:jakarta.mail-api:2.1.0")
    implementation("org.eclipse.angus:angus-core:1.0.0")
    implementation("org.eclipse.angus:smtp:1.0.0")

    // hibernate
    implementation("org.hibernate:hibernate-core-jakarta:5.6.14.Final")
}

tasks.war {
    archiveFileName.set("ROOT.war")
    archiveBaseName.set("ROOT")
}


tasks.register<Exec>("serve") {
    var warFile = tasks.war.get().archiveBaseName.get();
    if (warFile == "ROOT")  warFile = "/";
    commandLine("docker", "exec", "simplonweb-server-1","curl","--no-progress-meter", "--user", "tomcat:secret", "http://localhost:8080/manager/text/reload?path=$warFile")
    if(!File("build/libs/manager").exists()) {
      println("manager file does not exist")
      commandLine("docker", "exec","simplonweb-server-1", "cp", "-r", "/usr/local/tomcat/webapps.dist/manager", "/usr/local/tomcat/webapps/")
   }
}

tasks.register<Exec>("migrate") {
  commandLine("docker", "exec", "simplonweb-db-1", "psql", "-U","postgres", "-c", commandLine("cat ./init.sql"), "simplon" )
}

tasks.war { finalizedBy("serve")}
