plugins {
    id("parent-conventions")
}

val nativesLinux      = projects.cpuFeaturesJniNativesLinux.dependencyProject
val nativesWindows    = projects.cpuFeaturesJniNativesWindows.dependencyProject
val nativesMacos      = projects.cpuFeaturesJniNativesMacos.dependencyProject
val nativesMacosArm64 = projects.cpuFeaturesJniNativesMacosArm64.dependencyProject

group = "io.github.aecsocket"
version = "0.1.0-SNAPSHOT"
description = "Java bindings for Google cpu_features"
