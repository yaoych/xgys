plugins {
    kotlin("jvm") version "1.3.61"
}

group = "org.xgys"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    this.maven("http://maven.aliyun.com/nexus/content/groups/public")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}