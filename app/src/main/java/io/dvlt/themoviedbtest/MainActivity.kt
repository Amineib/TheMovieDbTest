package io.dvlt.themoviedbtest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.dvlt.themoviedbtest.presentation.navigation.NavigationGraph
import io.dvlt.themoviedbtest.presentation.screens.home.HomeScreen
import io.dvlt.themoviedbtest.presentation.theme.BaseMoviesTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.http.withCharset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            BaseMoviesTheme(darkTheme = false) {
                NavigationGraph(navController = navController)
            }
        }

        /*CoroutineScope(Dispatchers.IO).launch {
            HttpClient().use { client ->
                val response =
                    client.get(urlString = "https://api.themoviedb.org/3/movie/top_rated") {
                        headers {
                            bearerAuth(BuildConfig.tmdbApiKeyV4)
                            contentType(ContentType.Application.Json.withCharset(Charsets.UTF_8))
                        }
                    }

                if (response.status.isSuccess()) {
                    val bodyString = response.body<String>()
                    val bodyJson = JSONObject(bodyString)
                    Log.i("MainActivity", "Successful request: ${bodyJson.toString(4)}")
                } else {
                    Log.e("MainActivity", "Request failed: ${response.status}")
                }
            }
        }*/
    }

}
