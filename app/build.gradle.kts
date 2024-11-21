plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.karla.cookyhome"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.karla.cookyhome"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    //Se agrega en view binding para acceder a los componentes de una manera mas rapida
    buildFeatures {
        viewBinding = true
        dataBinding =true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity:1.8.0")
    implementation("com.google.firebase:firebase-auth-ktx:23.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Libreria de animaciones lottie
    implementation ("com.airbnb.android:lottie:6.0.1")
    //Librerias para la conexion a firebase
    implementation("com.google.firebase:firebase-bom:33.1.1")
    //Libreria para analytics de firebase
    implementation ("com.google.firebase:firebase-analytics:22.0.2")

    //Libreria para la base de datos de firebase(RealTime DataBase)
    implementation("com.google.firebase:firebase-database")
    implementation ("com.google.firebase:firebase-database:21.0.0")

    //Libreria para comprimir las imagenes que se suben a firebase
    implementation ("id.zelory:compressor:2.1.1")

}