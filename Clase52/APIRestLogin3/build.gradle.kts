
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    //alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    //id("com.google.devtools.ksp") version "1.9.24-1.0.20" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false

}

/*buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
}*/
