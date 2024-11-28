plugins {
    id("java")
    id("io.freefair.lombok") version "8.11"

}

group = "me.prexorjustin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.1")
    implementation("org.jetbrains:annotations:26.0.1")

    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
    testImplementation("org.mockito:mockito-all:2.0.2-beta")
}