plugins {
  id("java")
  id("war")
}

repositories { 
  mavenCentral()
}
// define jdk version
java { sourceCompatibility = JavaVersion.VERSION_17 }

dependencies { 
  compileOnly("jakarta.servlet:jakarta.servlet-api:5.0.0")
  compileOnly("org.postgresql:postgresql:42.3.7")
}

tasks.register<Exec>("serve") {
   commandLine("docker", "exec", "simplonweb-server-1","curl","--no-progress-meter", "--user", "tomcat:secret", "http://localhost:8080/manager/text/reload?path=/SimplonWeb")
   if(!File("build/libs/manager").exists()) {
     println("manager file does not exist")
     commandLine("docker", "exec","simplonweb-server-1", "cp", "-r", "/usr/local/tomcat/webapps.dist/manager", "/usr/local/tomcat/webapps/")
   }
}

tasks.register<Exec>("migrate") {
  commandLine("docker", "exec", "simplonweb-db-1", "psql", "-U","postgres", "-c", commandLine("cat ./init.sql"), "simplon" )
}

tasks.war { finalizedBy("serve")}
