package com.example.pokemonapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun DetailScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
        ){
        Text(
            text = "Detail Screen",
            fontSize = 28.sp,
            color = Color.Black


        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailScreenPreview() {

}