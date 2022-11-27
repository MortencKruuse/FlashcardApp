package com.example.flashcardapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flashcardapp.R


@Composable
fun Card() {

}

@Composable
fun CardText() {

}

@Composable
fun DeckText() {

}

@Composable
fun FlashCardDivider(modifier: Modifier = Modifier) {
    Divider(color = MaterialTheme.colors.background, thickness = 1.dp, modifier = modifier)
}

@Composable
fun Background(alpha: Float){
    Box(Modifier.fillMaxSize()){
            Image(painter = painterResource(id = R.drawable.background),
                contentDescription = "",
                modifier = Modifier.fillMaxSize().blur(6.dp).alpha(alpha),
                contentScale = ContentScale.Crop)


        }
    }