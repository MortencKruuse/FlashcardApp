package com.example.flashcardapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flashcardapp.R
import com.example.flashcardapp.ui.FlashCardDestination

@Composable
fun FlashCardBackgroundImage(
    allScreens: List<FlashCardDestination>,
    onTabSelected: (FlashCardDestination) -> Unit,
    currentScreen: FlashCardDestination
) {
    Surface(
        androidx.compose.ui.Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Box(Modifier.selectableGroup()) {
            allScreens.forEach { screen ->
                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(6.dp),
                    contentScale = ContentScale.Crop
                )

            }
        }
    }
}


