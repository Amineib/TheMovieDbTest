package io.dvlt.themoviedbtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.dvlt.themoviedbtest.presentation.navigation.NavigationGraph
import io.dvlt.themoviedbtest.presentation.theme.BaseMoviesTheme

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

    }

}
