plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.midterm.doixiucoffee_mobileapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.midterm.doixiucoffee_mobileapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")
    implementation("com.google.android.gms:play-services-tasks:18.1.0")
    implementation("com.google.firebase:firebase-firestore:25.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("de.hdodenhof:circleimageview:1.2.1")
    implementation ("com.google.android.material:material:1.2.0")

    //import retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    // import json converter
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    // import picasso
    implementation("com.squareup.picasso:picasso:2.8")
}