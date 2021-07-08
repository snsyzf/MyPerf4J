plugins {
    id("MyPerf4J.java-conventions")
}

dependencies {
    implementation(project(":MyPerf4J-ASM"))
    implementation(project(":MyPerf4J-Base"))
    implementation("org.openjdk.jmh:jmh-core:1.23")
    implementation("org.openjdk.jmh:jmh-generator-annprocess:1.23")
    testImplementation("junit:junit:4.13.2")
}
