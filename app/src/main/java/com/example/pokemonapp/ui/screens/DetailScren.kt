package com.example.pokemonapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DetailScreen(pokemonName:String,pokemonImage:Int) {
    Box(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
        ){
        Text(
            text = pokemonName,
            fontSize = 24.sp,
            color = Color.Red,
            fontWeight = FontWeight.Medium,
        )
        Image(
            painter = painterResource(id = pokemonImage),
            contentDescription ="" ,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(90.dp)
                .fillMaxSize()

            )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailScreenPreview() {

}