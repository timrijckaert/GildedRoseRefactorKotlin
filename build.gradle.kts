import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.6.10"
}

group = "com.gildedrose"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib"))

	val kotest = "5.3.2"
	testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotest")
	testImplementation("io.kotest:kotest-assertions-core:$kotest")
	testImplementation("io.kotest:kotest-property:$kotest")
}

tasks.test {
	useJUnitPlatform()
	testLogging {
		events("passed", "skipped", "failed")
	}
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType<KotlinCompile>().configureEach {
	kotlinOptions.jvmTarget = "1.8"
}
