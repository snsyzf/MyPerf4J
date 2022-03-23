plugins {
    id("MyPerf4J.java-conventions")
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

dependencies {
    implementation("org.ow2.asm:asm-commons:9.2") {
        exclude(module = "asm-tree")
        exclude(module = "asm-analysis")
    }
    implementation(project(":MyPerf4J-Core"))

    testImplementation("junit:junit:4.13.2")
}
tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    this.isZip64 = true
    this.archiveClassifier.set("")
    manifest {
        this.attributes("Premain-Class" to "cn.myperf4j.asm.PreMain")
    }
    relocate("org.objectweb.asm", "org.shaded.objectweb.asm")
}
