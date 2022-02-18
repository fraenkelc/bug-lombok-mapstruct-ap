plugins {
    java
}

allprojects {
    repositories {
        mavenCentral()
    }

}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(11))

dependencies {
    annotationProcessor(project(":ap"))
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")

    compileOnly("org.projectlombok:lombok:1.18.22")
    compileOnly("org.mapstruct:mapstruct:1.4.2.Final")
    compileOnly(project(":ap"))
}

tasks.compileJava {
    options.compilerArgs.addAll(listOf("-verbose", "-XprintRounds", "-XprintProcessorInfo", "-Xlint"))
}
