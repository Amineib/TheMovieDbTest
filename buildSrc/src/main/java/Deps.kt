object Deps {
    val androidCoreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val lifecycleRuntimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}" }
    val ktor by lazy { "io.ktor:ktor-client-android:${Versions.kTor}" }
    val ktorClientAuth by lazy { "io.ktor:ktor-client-auth:${Versions.kTor}" }
    val jUnit by lazy { "junit:junit:${Versions.jUnit}" }
    val androidxJunit by lazy { "androidx.test.ext:junit:${Versions.androidXTestJUnit}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espressoCore}" }

    //Compose
    val composeUi by lazy { "androidx.compose.ui:ui:${Versions.compose}" }
    val composeTooling by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.compose}" }
    val composeMaterial by lazy { "androidx.compose.material:material:${Versions.compose}" }
    val composeMaterialIconsExtended by lazy { "androidx.compose.material:material-icons-extended:${Versions.compose}" }
    val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }

    //Paging 3
    val paging by lazy { "androidx.paging:paging-runtime-ktx:${Versions.paging}" }
    val pagingCompose by lazy { "androidx.paging:paging-compose:${Versions.pagingCompose}" }

    //Navigation
    val navigationCompose by lazy { "androidx.navigation:navigation-compose:${Versions.navigationCompose}" }

    //Dagger Hilt
    val daggerHilt by lazy { "com.google.dagger:hilt-android:${Versions.daggerHilt}" }
    val daggerHiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}" }
    val daggerHiltNavigation by lazy { "androidx.hilt:hilt-navigation-fragment:${Versions.daggerHiltNavigation}" }
    val daggerHiltNavigationCompose by lazy { "androidx.hilt:hilt-navigation-compose:${Versions.daggerHiltNavigationCompose}" }

    //Retrofit
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val retrofitGsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    val okHttpLogging by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptor}" }
    val jsonSerialization by lazy { "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.jsonSerialization}" }

    //Coil
    val coil by lazy { "io.coil-kt:coil-compose:${Versions.coil}" }

}