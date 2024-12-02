plugins {
  id("java")
  kotlin("jvm")
}

group = "com.adventofcode"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation("com.google.guava:guava:33.3.1-jre")
  compileOnly("org.jetbrains:annotations:26.0.1")

  testImplementation(platform("org.junit:junit-bom:5.10.0"))
  testImplementation("org.junit.jupiter:junit-jupiter")
  testImplementation("org.assertj:assertj-core:3.26.3")
}

tasks.test {
  useJUnitPlatform()
}

kotlin {
  jvmToolchain(23)
}