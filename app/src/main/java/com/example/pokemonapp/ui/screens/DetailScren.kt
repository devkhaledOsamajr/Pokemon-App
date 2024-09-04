package com.example.pokemonapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.pokemonapp.R
import com.example.pokemonapp.model.Pokemons


@Composable
fun DetailScreen(pokemonName: String, pokemonImage: Int, navController: NavHostController) {
    val pokemons = PokemonList()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        LazyRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier

                .fillMaxWidth()
        ) {
            items(pokemons) { pokemon ->
                PokemonListRowUi(pokemons = pokemon, navController = navController)

            }
        }
        HorizontalDivider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (player1, player2, dice) = createRefs()


                Image(
                    painter = painterResource(id = pokemonImage),
                    contentDescription = "player 1 hero",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(horizontal = 28.dp, vertical = 20.dp)
                        .size(100.dp)
                        .constrainAs(player1) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }

                )
                Image(
                    painter = painterResource(id = R.drawable.dice),
                    contentDescription = "dice random",
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .size(60.dp)
                        .constrainAs(dice) {
                            start.linkTo(player1.end)
                            bottom.linkTo(player1.bottom)
                        }

                )
            }
        }


    }


}


@Composable
fun PokemonListRowUi(
    modifier: Modifier = Modifier,
    pokemons: Pokemons,
    navController: NavHostController
) {
    ConstraintLayout(
        Modifier.fillMaxWidth()
    ) {
        val (heroImage, horizontalLine, player1, player2, randomDice) = createRefs()



        Image(
            painter = painterResource(id = pokemons.icon),
            contentDescription = "Hero",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .padding(10.dp)
                .size(60.dp)
                .constrainAs(heroImage) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }

        )

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailScreenPreview() {

}