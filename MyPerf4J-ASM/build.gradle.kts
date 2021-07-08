plugins {
    id("MyPerf4J.java-conventions")
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

dependencies {
    implementation("org.ow2.asm:asm-commons:8.0.1")
    implementation("org.ow2.asm:asm-util:8.0.1")
    implementation("org.ow2.asm:asm-tree:8.0.1")
    implementation("org.ow2.asm:asm-analysis:8.0.1")
    implementation(project(":MyPerf4J-Core"))
}
tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    this.isZip64 = true
    this.archiveClassifier.set("")
    manifest {
        this.attributes("Premain-Class" to "cn.myperf4j.asm.PreMain")
    }
    relocate("org.objectweb.asm", "org.shaded.objectweb.asm")
}
