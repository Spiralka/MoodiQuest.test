plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.rest-assured:rest-assured:5.4.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")

    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.26.3")
    testImplementation("io.qameta.allure:allure-junit5:2.29.0")
    implementation("io.qameta.allure:allure-rest-assured:2.29.0")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
