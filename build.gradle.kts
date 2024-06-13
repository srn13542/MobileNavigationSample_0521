
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.google.gms.google-services") version "4.4.1" apply false

}
// 프로젝트 루트 디렉토리의 build.gradle 파일 (예: /build.gradle)
buildscript {
    repositories {
        google()
        mavenCentral()
    }
}