package com.example.pokemonapp.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.pokemonapp.R
import com.example.pokemonapp.navigation.Screen
import com.example.pokemonapp.model.Pokemons

@Composable
fun PokemonUi(
    modifier: Modifier,
    pokemon: Pokemons,
    navController: NavHostController
) {

    val backgroundColor = when (pokemon.type) {
        "grass" -> colorResource(id = R.color.green)
        "fire" -> colorResource(id = R.color.red)
        "water" -> colorResource(id = R.color.blue)
        else -> Color.Gray
    }

    val backgroundTextColor = when (pokemon.type) {
        "grass" -> colorResource(id = R.color.green_light)
        "fire" -> colorResource(id = R.color.red_light)
        "water" -> colorResource(id = R.color.blue_light)
        else -> Color.Gray
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 10.dp)
            .size(150.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 24.dp, vertical = 10.dp)
            .clickable {
                navController.navigate(
                    Screen.Detail
                        .createRoute(
                            "${pokemon.name}",
                            pokemon.icon
                            )
                )
            }


    ) {
        ConstraintLayout(
            modifier
                .fillMaxSize()
        ) {
            val (
                pokemonName,
                pokemonType,
                pokemonAttackPwr,
                pokemonAttackPwrValue,
                pokemonDefensePwr,
                pokemonDefensePwrValue,
                pokemonIcon
            ) = createRefs()

            Text(
                text = "${pokemon.name}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .statusBarsPadding()
                    .constrainAs(pokemonName) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
            )



            Text(

                text = "${pokemon.type}",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 10.dp)
                    .constrainAs(pokemonType) {
                        top.linkTo(pokemonName.bottom)
                    }
                    .background(backgroundTextColor, shape = RoundedCornerShape(16.dp))
                    .padding(10.dp)
            )

            Text(
                text = "Attack:",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 12.dp)
                    .constrainAs(pokemonAttackPwr) {
                        start.linkTo(pokemonType.end)
                        top.linkTo(pokemonName.bottom)
                        top.linkTo(pokemonType.top)
                    }
            )
            Text(
                text = "${pokemon.attackPwr}",
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .constrainAs(pokemonAttackPwrValue) {
                        start.linkTo(pokemonAttackPwr.end)
                        top.linkTo(pokemonName.bottom)
                    }
            )
            Text(
                text = "Defense:",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .constrainAs(pokemonDefensePwr) {
                        top.linkTo(pokemonAttackPwr.bottom)
                        start.linkTo(pokemonAttackPwr.start)
                    }
            )
            Text(
                text = "${pokemon.defensePwr}",
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier

                    .constrainAs(pokemonDefensePwrValue) {
                        start.linkTo(pokemonDefensePwr.end)
                        top.linkTo(pokemonAttackPwr.bottom)
                    }
            )

            Image(
                painter = painterResource(pokemon.icon),
                contentDescription = "Bullbasaur",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(50.dp),
                        color = colorResource(id = R.color.white)
                    )
                    .constrainAs(pokemonIcon) {
                        end.linkTo(parent.end)
                        bottom.linkTo(pokemonDefensePwr.bottom)
                    }
                    .size(100.dp)
            )

        }
    }

}


@Composable
fun HomeScreen(navController: NavHostController) {
  /*  val pokemons = listOf(
        Pokemons("Bullbasaur", "grass", 65, 65, R.drawable.bulbasaur),
        Pokemons("Ivysaur", "grass", 80, 80, R.drawable.ivysaur),
        Pokemons("Venusaur", "grass", 122, 120, R.drawable.venusaur),
        Pokemons("Charmander", "fire", 60, 50, R.drawable.charmander),
        Pokemons("Charmeleon", "fire", 80, 65, R.drawable.charmeleon),
        Pokemons("Charizard", "fire", 159, 115, R.drawable.charizard),
        Pokemons("Squirtle", "water", 50, 65, R.drawable.squirtle),
        Pokemons("Wartortle", "water", 65, 80, R.drawable.wartortle),
        Pokemons("Blastoise", "water", 135, 115, R.drawable.blastoise)
    )
   */
    val pokemons = PokemonList()

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        items(pokemons) { pokemon ->
            PokemonUi(Modifier, pokemon = pokemon, navController = navController)


        }


    }
}


fun PokemonList():List<Pokemons> {
    return  listOf(
        Pokemons("Bullbasaur", "grass", 65, 65, R.drawable.bulbasaur),
        Pokemons("Ivysaur", "grass", 80, 80, R.drawable.ivysaur),
        Pokemons("Venusaur", "grass", 122, 120, R.drawable.venusaur),
        Pokemons("Charmander", "fire", 60, 50, R.drawable.charmander),
        Pokemons("Charmeleon", "fire", 80, 65, R.drawable.charmeleon),
        Pokemons("Charizard", "fire", 159, 115, R.drawable.charizard),
        Pokemons("Squirtle", "water", 50, 65, R.drawable.squirtle),
        Pokemons("Wartortle", "water", 65, 80, R.drawable.wartortle),
        Pokemons("Blastoise", "water", 135, 115, R.drawable.blastoise)
    )
}

