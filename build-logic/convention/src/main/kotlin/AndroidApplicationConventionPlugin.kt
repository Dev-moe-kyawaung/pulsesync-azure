plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("jacoco")
}

android {
    testOptions.unitTests.isReturnDefaultValues = true
}

tasks.withType<Test>().configureEach {
    jacoco.includeNoLocationClasses = true
    jacoco.excludes = listOf("jdk.internal.*")
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn("testDebugUnitTest")
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    sourceDirectories.setFrom("${project.projectDir}/src/main/java")
    classDirectories.setFrom("${project.buildDir}/intermediates/javac/debug/classes")
    executionData.setFrom("${project.buildDir}/jacoco/testDebugUnitTest.exec")
}

