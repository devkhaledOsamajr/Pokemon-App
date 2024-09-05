package com.example.pokemonapp.ui.screens

import android.util.MutableBoolean
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.pokemonapp.R
import com.example.pokemonapp.model.Pokemons
import kotlin.random.Random


@Composable
fun DetailScreen(
    pokemonName: String,
    pokemonImage: Int,
    navController: NavHostController
) {
    val pokemons = PokemonList()
    var clickCount by remember { mutableStateOf(0) }
    var randomHero by remember { mutableStateOf(pokemons.random()) }
    var randomHeroAttack by remember { mutableStateOf(getAttackValue(randomHero.icon)) }
    val randomHeroDefense = getDefenseValue(randomHero.icon)
    var selectedImage by remember { mutableStateOf(pokemonImage) }
    val selectedHeroAttack = getAttackValue(selectedImage)
    val selectedHeroDefense = getDefenseValue(selectedImage)
    var showText by remember {
        mutableStateOf(false)
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(pokemons) { pokemon ->
                PokemonListRowUi(pokemons = pokemon) {
                    selectedImage = pokemon.icon
                }
            }
        }

        HorizontalDivider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )

        AnimatedTextField(showText)



        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 30.dp)
                .fillMaxHeight()
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (
                    player1Image, player1, player2, player2Image, dice,
                    player1Attack, player1Defense, player2Attack, player2Defense, startBtn
                ) = createRefs()

                createHorizontalChain(
                    player1Image, dice, player2Image,
                    chainStyle = ChainStyle.Spread
                )
                createHorizontalChain(player1, player2, chainStyle = ChainStyle.SpreadInside)

                Text(
                    text = "Player 1:   ",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(horizontal = 28.dp)
                        .constrainAs(player1) {
                            top.linkTo(parent.top)
                            start.linkTo(player1Image.start)
                        }
                )

                Image(
                    painter = painterResource(id = selectedImage),
                    contentDescription = "Player 1 hero",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .padding(horizontal = 28.dp, vertical = 20.dp)
                        .size(100.dp)
                        .constrainAs(player1Image) {
                            top.linkTo(player1.bottom)
                            bottom.linkTo(player2Image.bottom)
                        }
                )

                Text(
                    text = "Attack: $selectedHeroAttack",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(horizontal = 28.dp)
                        .constrainAs(player1Attack) {
                            top.linkTo(player1Image.bottom)
                            start.linkTo(player1Image.start)
                        }
                )

                Text(
                    text = "Defense: $selectedHeroDefense",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(horizontal = 28.dp)
                        .constrainAs(player1Defense) {
                            top.linkTo(player1Attack.bottom)
                            start.linkTo(player1Attack.start)
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.dice),
                    contentDescription = "Dice",
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .size(40.dp)
                        .constrainAs(dice) {
                            start.linkTo(player1Image.end)
                            bottom.linkTo(player1Image.bottom)
                        }
                        .clickable {
                            if (clickCount < 2) {
                                randomHero = pokemons.random()
                                randomHeroAttack = getAttackValue(randomHero.icon)
                                clickCount++
                            }
                        }
                )

                Text(
                    text = "Player 2:",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(horizontal = 28.dp)
                        .constrainAs(player2) {
                            bottom.linkTo(player2Image.top)
                            start.linkTo(player2Image.start)
                        }
                )

                Image(
                    painter = painterResource(id = randomHero.icon),
                    contentDescription = "Player 2 hero",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .padding(horizontal = 28.dp, vertical = 20.dp)
                        .size(100.dp)
                        .constrainAs(player2Image) {
                            start.linkTo(dice.end)
                            end.linkTo(parent.end)
                            bottom.linkTo(player1Image.bottom)
                        }
                )

                Text(
                    text = "Attack: $randomHeroAttack",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(horizontal = 28.dp)
                        .constrainAs(player2Attack) {
                            top.linkTo(player2Image.bottom)
                            start.linkTo(player2Image.start)
                        }
                )

                Text(
                    text = "Defense: $randomHeroDefense",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(horizontal = 28.dp)
                        .constrainAs(player2Defense) {
                            top.linkTo(player2Attack.bottom)
                            start.linkTo(player2Attack.start)
                        }
                )

                Button(
                    onClick = {
                        showText = !showText
                              },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = colorResource(id = R.color.yellow),
                        containerColor = colorResource(id = R.color.blue_btn)
                    ),
                    modifier = Modifier
                        .padding(12.dp)
                        .constrainAs(startBtn) {
                            top.linkTo(player2Defense.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                ) {
                    Text(
                        text = "Play",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 2.sp
                    )
                }

            }
        }
    }
}



@Composable
fun AnimatedTextField(isVisible:Boolean) {

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(
            animationSpec = tween(durationMillis = 1000)),
        exit = fadeOut(
            animationSpec = tween(durationMillis = 1000)),

    ) {
        Text(
            text = "player Win",
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .padding(vertical = 28.dp)


        )

    }
}


fun getDefenseValue(icon: Int): Any {
    return when (icon) {
        R.drawable.charmeleon -> 65
        R.drawable.venusaur -> 120
        R.drawable.bulbasaur -> 65
        R.drawable.blastoise -> 115
        R.drawable.squirtle -> 65
        R.drawable.charizard -> 115
        R.drawable.ivysaur -> 80
        R.drawable.wartortle -> 80
        R.drawable.charmander -> 50
        else -> {
            10
        }

    }

}

fun getAttackValue(icon: Int): Any {
    return when (icon) {
        R.drawable.charmeleon -> 80
        R.drawable.venusaur -> 122
        R.drawable.bulbasaur -> 65
        R.drawable.blastoise -> 135
        R.drawable.squirtle -> 50
        R.drawable.charizard -> 159
        R.drawable.ivysaur -> 80
        R.drawable.wartortle -> 65
        R.drawable.charmander -> 60
        else -> {
            10
        }
    }
}


@Composable
fun PokemonListRowUi(
    modifier: Modifier = Modifier,
    pokemons: Pokemons,
    onClick: () -> Unit

) {
    ConstraintLayout(
        Modifier.fillMaxWidth()
    ) {
        val (heroImage) = createRefs()



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
                .clickable(onClick = onClick)

        )

    }
}

fun getRandomPokemons(pokemons: List<Pokemons>): Pokemons {
    return pokemons[Random.nextInt(pokemons.size)]
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailScreenPreview() {

}