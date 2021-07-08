plugins {
    `java-library`
    `maven-publish`
}

repositories {
    maven("https://maven.aliyun.com/repository/central")
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.aliyun.com/repository/jcenter")
    maven("https://maven.aliyun.com/repository/spring")
}

group = "MyPerf4J"
version = "3.3.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_7

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
