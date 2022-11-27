package com.example.flashcardapp.ui

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.flashcardapp.ui.theme.FlashcardAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.db.DeckDatabase
import com.example.flashcardapp.data.repositories.DeckRepository
import com.example.flashcardapp.data.viewmodels.DeckViewModel
import com.example.flashcardapp.data.viewmodels.ViewModelFactory
import com.example.flashcardapp.ui.components.FlashCardTabRow
import com.example.flashcardapp.ui.theme.FlashcardAppTheme
import kotlinx.coroutines.flow.SharingStarted


class FlashCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
                FlashcardAppTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {

                        val owner = LocalViewModelStoreOwner.current

                        owner?.let {
                            val viewModel: DeckViewModel = viewModel(
                                it,
                                "MainViewModel",
                                ViewModelFactory(
                                    LocalContext.current.applicationContext
                                            as Application
                                )
                            )

                            ScreenSetup(viewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenSetup(viewModel: DeckViewModel) {

    val allDecks by viewModel.allDecks.observeAsState(listOf())
    val searchResults by viewModel.searchResults.observeAsState(listOf())

    MainScreen(
        allDecks = allDecks,
        searchResults = searchResults,
        viewModel = viewModel
    )
}

@Composable
fun MainScreen(
    allDecks: List<Deck>,
    searchResults: List<Deck>,
    viewModel: DeckViewModel
) {
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


/*
setContent {
        RoomDemoTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {

                val owner = LocalViewModelStoreOwner.current

                owner?.let {
                    val viewModel: MainViewModel = viewModel(
                        it,
                        "MainViewModel",
                        MainViewModelFactory(
                          LocalContext.current.applicationContext
                                                      as Application)
                    )

                    ScreenSetup(viewModel)
                }
            }
        }
 */