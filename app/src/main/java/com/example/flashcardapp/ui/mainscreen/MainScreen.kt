package com.example.flashcardapp.ui.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.flashcardapp.R
import com.example.flashcardapp.ui.components.FlashCardAlertDialog
import com.example.flashcardapp.ui.components.FlashCardDivider
import java.util.*


@Composable
fun MainScreen(
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "Overview Screen" }
    ) {
        MainMenu()
    }
}


@Composable
fun MainMenu() {
    Image(painter = painterResource(id = R.drawable.background), contentDescription = "")
}


private val RallyDefaultPadding = 12.dp

