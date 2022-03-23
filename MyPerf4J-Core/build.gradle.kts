plugins {
    id("MyPerf4J.java-conventions")
}

dependencies {
    api(project(":MyPerf4J-Base"))
    testImplementation("junit:junit:4.13.2")
}
