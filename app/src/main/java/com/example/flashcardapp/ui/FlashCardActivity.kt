package com.example.flashcardapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.repositories.DeckRepository
import com.example.flashcardapp.data.viewmodels.DeckViewModel
import com.example.flashcardapp.ui.components.FlashCardTabRow
import com.example.flashcardapp.ui.theme.FlashcardAppTheme
import kotlinx.coroutines.flow.SharingStarted
import javax.inject.Inject


class FlashCardActivity : ComponentActivity() {

    @Inject
    lateinit var deckRepository: DeckRepository

    val deckRepositoryState by lazy {
        deckRepository.getDeck()
                /*
            .stateIn(
                lifecycleScope,
                started = SharingStarted.WhileSubscribed(),
                emptyList()
            )*/
    }

/*
    //TODO DB STUFF: Research appropriate location for line below and if should be "lateInit var"
    var deckViewModel: DeckViewModel = ViewModelProvider(this).get(DeckViewModel::class.java)

    init {
        val cards = mutableListOf<Card>(
            Card(1, "Q", "A", 1),
            Card(2, "Q", "A", 1),
            Card(3, "Q", "A", 1),
            Card(4, "Q", "A", 1),
            Card(5, "Q", "A", 1),
            Card(6, "Q", "A", 1),
            Card(7, "Q", "A", 1),
            Card(8, "Q", "A", 1),
            Card(9, "Q", "A", 1),
            Card(10, "Q", "A", 1)
        )

        val decks = mutableListOf<Deck>(
            Deck(1, "DeckOne", cards)
        )
        deckViewModel.addDeck(decks.first())
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlashCardApp()

        }
    }
}

@Composable
fun FlashCardApp() {
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