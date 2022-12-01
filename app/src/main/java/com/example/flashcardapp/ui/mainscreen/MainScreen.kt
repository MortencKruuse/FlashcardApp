package com.example.flashcardapp.ui.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.flashcardapp.ui.components.Background
import com.example.flashcardapp.ui.components.BackgroundBox


@Composable
fun MainScreen(
    onNavigateToDeck: () -> Unit,
    onNavigateToTopic: () -> Unit
) {
    Background(1f)
    BackgroundBox()
    Column(
        modifier = Modifier
            .padding(48.dp)
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "Overview Screen" }

    ) {
        MainMenu(onNavigateToDeck, onNavigateToTopic)
    }
}


@Composable
fun MainMenu(onNavigateToDeck: () -> Unit, onNavigateToTopic: () -> Unit) {
    Button(onClick = onNavigateToDeck) {
        Text(text = "Go to deck screen")
    }
    Button(onClick = onNavigateToTopic) {

    }
}


private val RallyDefaultPadding = 12.dp

