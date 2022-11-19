plugins {
  java
  war
  id("com.bmuschko.tomcat") version "2.7.0"
}

repositories { 
  mavenCentral()
}
// define jdk version
java { sourceCompatibility = JavaVersion.VERSION_17 }

dependencies { 
  compileOnly("jakarta.servlet:jakarta.servlet-api:5.0.0")
  compileOnly("org.eclipse.angus:jakarta.mail:1.0.0")
  compileOnly("org.hibernate.orm:hibernate-core:6.0.0.Final")
}

tasks.war {
    archiveFileName.set("ROOT.war")
    archiveBaseName.set("ROOT")
}


tasks.register<Exec>("serve") {
    var warFile = tasks.war.get().archiveBaseName.get();
    if (warFile == "ROOT")  warFile = "/";
    commandLine("docker", "exec", "simplonweb-server-1","curl","--no-progress-meter", "--user", "tomcat:secret", "http://localhost:8080/manager/text/reload?path="+warFile)
    if(!File("build/libs/manager").exists()) {
      println("manager file does not exist")
      commandLine("docker", "exec","simplonweb-server-1", "cp", "-r", "/usr/local/tomcat/webapps.dist/manager", "/usr/local/tomcat/webapps/")
   }
}

tasks.register<Exec>("migrate") {
  commandLine("docker", "exec", "simplonweb-db-1", "psql", "-U","postgres", "-c", commandLine("cat ./init.sql"), "simplon" )
}

tasks.war { finalizedBy("serve")}
