package com.example.flashcardapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.flashcardapp.ui.theme.FlashcardAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.viewmodels.DeckViewModel
import com.example.flashcardapp.ui.components.Card
import com.example.flashcardapp.ui.components.FlashCardTabRow



class FlashCardActivity : ComponentActivity() {
    //TODO DB STUFF: Research appropriate location for line below
    val commit = ""
    private lateinit var deckViewModel: DeckViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //TODO DB STUFF: maybe not here the viewmodel should be initialized. Research later on.
            deckViewModel = ViewModelProvider(this).get(DeckViewModel::class.java)
            FlashCardApp()

        }
    }
}

@Composable
fun FlashCardApp(){
    FlashcardAppTheme {
        //remember keeps a value (any value) consistent across recompositions.
        //mutableStateOf returns a MutableState. Think mutableListOf.
        //MutableState is just a thing that holds a value, where Compose will automatically observe changes to the value. Think MutableLiveData, but you don't need to call observe yourself.
        var currentScreen: FlashCardDestination by remember { mutableStateOf(MainScreen) }
        Scaffold(
            bottomBar = {
                FlashCardTabRow(
                    allScreens = flashCardTabRowScreens,
                    onTabSelected = { screen -> currentScreen = screen },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                currentScreen.screen()
            }
        }
    }
}