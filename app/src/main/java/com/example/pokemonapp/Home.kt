package com.example.pokemonapp

import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.pokemonapp.model.Pokemons
import com.example.pokemonapp.ui.theme.PokemonAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            PokemonList()

        }

    }
}

@Composable
fun PokemonList(modifier: Modifier = Modifier) {
    val pokemonList = listOf(
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
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        items(pokemonList) { pokemon ->
            PokemonUi(pokemon = pokemon)


        }
    }

}


@Composable
fun PokemonUi(modifier: Modifier = Modifier, pokemon: Pokemons) {
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
            .padding(16.dp)
            .size(150.dp)
            .background(color = backgroundColor as Color, shape = RoundedCornerShape(16.dp))
            .padding(20.dp)

    ) {
        ConstraintLayout(modifier.fillMaxWidth()) {
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
                modifier = Modifier.constrainAs(pokemonName) { start.linkTo(parent.start) }
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
                        top.linkTo(parent.top)
                    }
                    .size(100.dp)
            )

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PokemonUiPreview() {

}