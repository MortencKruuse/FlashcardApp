package com.example.flashcardapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.example.flashcardapp.ui.FlashCardDestination
import androidx.compose.material.Surface
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flashcardapp.R

@Composable
fun FlashCardBackgroundImage(
    allScreens: List<FlashCardDestination>,
    onTabSelected: (FlashCardDestination) -> Unit,
    currentScreen: FlashCardDestination
) {

    Surface(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background image"
        )


    }


}