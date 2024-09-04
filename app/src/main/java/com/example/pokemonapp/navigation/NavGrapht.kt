package com.example.pokemonapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokemonapp.Util.Constans
import com.example.pokemonapp.ui.screens.DetailScreen
import com.example.pokemonapp.ui.screens.HomeScreen


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(Constans.POKEMON_NAME_KEY) { type = NavType.StringType },
                navArgument(Constans.POKEMON_IMAGE_KEY) { type = NavType.IntType},


            )
        ) { backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString(Constans.POKEMON_NAME_KEY)
            val pokemonImage = backStackEntry.arguments?.getInt(Constans.POKEMON_IMAGE_KEY)
            if (pokemonName != null && pokemonImage != null) {
                DetailScreen(pokemonName, pokemonImage,navController)
            }
        }

    }
}