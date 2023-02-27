
buildscript {
ext {
queryDslVersion = "5.0.0"
}
}

plugins {
id 'java'
id 'war'
id 'org.springframework.boot' version '2.7.8'
id 'io.spring.dependency-management' version '1.0.15.RELEASE'
id "com.ewerk.gradle.plugins.querydsl" version "1.0.10"
}

group = 'com.portal'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

configurations {
compileOnly {
extendsFrom annotationProcessor
}
querydsl.extendsFrom compileClasspath
}

repositories {
mavenCentral()
}

dependencies {
implementation 'org.springframework.boot:spring-boot-starter-actuator'
implementation 'org.springframework.boot:spring-boot-starter-batch'
implementation 'org.springframework.batch:spring-batch-core'
implementation 'org.springframework.boot:spring-boot-starter-data-jdbc'
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'com.h2database:h2'
implementation 'org.springframework.boot:spring-boot-starter-oauth2-resource-server'
implementation 'org.springframework.boot:spring-boot-starter-security'
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:2.3.0'
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

	implementation 'io.springfox:springfox-boot-starter:3.0.0'
	implementation 'io.springfox:springfox-swagger-ui:3.0.0'

	implementation "com.querydsl:querydsl-jpa:${queryDslVersion}"
	implementation "com.querydsl:querydsl-apt:${queryDslVersion}"
	implementation "com.querydsl:querydsl-core:${queryDslVersion}"
	implementation 'commons-dbcp:commons-dbcp:1.4'
//	implementation 'jakarta.annotation:jakarta.annotation-api:2.1.1'

	implementation 'org.apache.commons:commons-dbcp2:2.9.0'

	implementation 'org.json:json:20220320'
	implementation 'org.apache.commons:commons-collections4:4.0'




	compileOnly 'org.projectlombok:lombok'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	runtimeOnly 'com.mysql:mysql-connector-j'
	annotationProcessor 'org.springframework.boot:spring-boot-configuration-processor'
	annotationProcessor 'org.projectlombok:lombok'
	providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.batch:spring-batch-test'
	testImplementation 'org.springframework.security:spring-security-test'
}

tasks.named('test') {
useJUnitPlatform()
}

def querydslDir = "$buildDir/generated/querydsl"

querydsl {
jpa = true
querydslSourceDIr = querydslDir
}

sourceSets {
main.java.srcDir querydslDir
}
compileQuerydsl {
options.annotationProcessorPath = configureations.querydsl
}
