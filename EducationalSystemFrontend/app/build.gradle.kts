plugins {
    id("com.android.application")
}

android {
    namespace = "com.educationalsystem"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.educationalsystem"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(files("libs\\fastjson-1.2.76.jar"))
    implementation(files("libs\\jackson-datatype-jsr310-2.11.2.jar"))
//    implementation(files("libs\\jackson-databind-2.13.3.jar"))
//    implementation(files("libs\\jackson-core-2.13.3.jar"))
//    implementation(files("libs\\jackson-annotations-2.13.3.jar"))
//    implementation(files("libs\\jackson-datatype-jdk8-2.13.3.jar"))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.fasterxml.jackson.core:jackson-core:2.11.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.11.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.2")


}