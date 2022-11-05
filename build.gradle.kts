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

// task migrate(type:Exec) {
//     commandLine("docker', 'exec', 'booking-db-1', 'psql', '-U', 'postgres', '-f', '/usr/local/src/schema.sql', 'booking')
// }
//
// task serve(type:Exec) {
//    commandLine('docker', 'exec', 'booking-server-1','curl','--no-progress-meter', '--user', 'tomcat:secret', 'http://localhost:8080/manager/text/reload?path=/booking-1.0')
// }

// war.finalizedBy(serve)
