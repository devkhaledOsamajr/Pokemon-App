package com.example.pokemonapp.navigation

import com.example.pokemonapp.Util.Constans

sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object Detail :
        Screen(
             route = "detail_screen/{${Constans.POKEMON_NAME_KEY}}/{${Constans.POKEMON_IMAGE_KEY}}"

    ) {
        fun createRoute(
            pokemonName: String,
            pokemonImage: Int,

        ) =
            "detail_screen/$pokemonName/$pokemonImage"
    }

}