package io.dvlt.themoviedbtest.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.dvlt.themoviedbtest.presentation.screens.details.MovieDetailsScreen
import io.dvlt.themoviedbtest.presentation.screens.home.HomeScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(navController = navController, startDestination = Route.HOME ) {
        composable(route = Route.HOME) {
            HomeScreen(
                onMovieClicked = {
                    navController.navigate(Route.DETAILS + "/$it")
                }
            )
        }

        composable(
            route = Route.DETAILS +  "/{movieId}",
            arguments = listOf(
                navArgument(
                    name = "movieId"
                ){
                    type = NavType.IntType
                }
            )
        ) {
            val movieId by remember {
                mutableStateOf(it.arguments?.getInt("movieId"))
            }
            movieId?.let {
                MovieDetailsScreen(movieId = it)
            }
        }
    }
}