import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
plugins {
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	kotlin("plugin.jpa") version "1.7.22"
}

group = "com.vinit"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	//logging
	implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")
	//Reactive
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")


	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webflux")


	testImplementation("io.mockk:mockk:1.10.0")
	testImplementation("com.ninja-squad:springmockk:3.0.1")
	testImplementation("io.kotlintest:kotlintest-assertions:3.4.2") //Assertions shouldBe etc.

	//Async testing
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")


}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

sourceSets{
	test {
		java {
			setSrcDirs(listOf("src/test/integrationTest", "src/test/unitTest"))
		}
	}
}
