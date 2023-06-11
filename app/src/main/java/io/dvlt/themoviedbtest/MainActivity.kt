package io.dvlt.themoviedbtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
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
        }
    }

}
