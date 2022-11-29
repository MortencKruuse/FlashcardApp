package com.example.flashcardapp.ui.components

import android.graphics.Color.alpha
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
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
                modifier = Modifier
                    .fillMaxSize()
                    .blur(6.dp)
                    .alpha(alpha),
                contentScale = ContentScale.Crop)


        }
    }

@Composable
fun BackgroundBox(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .alpha(0.6f)
            .clip(CutCornerShape(topStart = 8.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 8.dp))
            .background(MaterialTheme.colors.background)
    )
}