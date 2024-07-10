plugins {
    kotlin("jvm") version "2.0.0"
}

kotlin {
    jvmToolchain(17)
}

repositories {
    mavenLocal()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    mavenCentral()
}

dependencies {
    implementation("com.cronutils:cron-utils:9.2.1")
    // logger
    implementation("org.slf4j:slf4j-simple:2.0.3")
    // infinitic
    version = "0.14.1-SNAPSHOT"
    implementation("io.infinitic:infinitic-client:$version")
    implementation("io.infinitic:infinitic-worker:$version")
}

task<JavaExec>("run") {
    group = "infinitic"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("io.infinitic.playbook.kotlin.WorkerKt")
}

task<JavaExec>("async-method-start") {
    group = "infinitic"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("io.infinitic.playbook.kotlin.asyncMethod.StartKt")
}

task<JavaExec>("async-method-stop") {
    group = "infinitic"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("io.infinitic.playbook.kotlin.asyncMethod.StopKt")
}

task<JavaExec>("scheduler-start") {
    group = "infinitic"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("io.infinitic.playbook.kotlin.scheduler.StartKt")
}

task<JavaExec>("scheduler-stop") {
    group = "infinitic"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("io.infinitic.playbook.kotlin.scheduler.StopKt")
}
