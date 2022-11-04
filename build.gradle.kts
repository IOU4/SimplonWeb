plugins { application }

repositories { mavenCentral() }
// define jdk version
java { sourceCompatibility = JavaVersion.VERSION_17 }

dependencies { 
  implementation("org.postgresql:postgresql:42.5.0")
    implementation("org.eclipse.angus:jakarta.mail:1.0.0")
}

tasks { 
  named<JavaExec>("run") {
    standardInput = System.`in` 
    file(".env").readLines().forEach {
        environment(it.split("=")[0], it.split("=")[1])
      }
  } 
  jar {
      manifest {
        attributes["Main-Class"] = "simplonclone.Main"
        attributes["Class-Path"] = configurations.compileClasspath.get().joinToString(" ") { it.absolutePath }
      }
    }
}

application { mainClass.set("simplonclone.Main") }
