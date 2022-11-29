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
import com.example.flashcardapp.ui.theme.FlashcardAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.FlashcardViewModel
import com.example.flashcardapp.data.ViewModelFactory
import com.example.flashcardapp.ui.mainscreen.MainScreen


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
                            val viewModel: FlashcardViewModel = viewModel(
                                it,
                                "MainViewModel",
                                ViewModelFactory(
                                    LocalContext.current.applicationContext
                                            as Application
                                )
                            )

                            ScreenSetup(viewModel)
                            MyAppNavHost()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenSetup(viewModel: FlashcardViewModel) {

    val allDecks by viewModel.allDecks.observeAsState(listOf())
    val searchResults by viewModel.deckSearchResults.observeAsState(listOf())


    MainSetup(
        allDecks = allDecks,
        searchResults = searchResults,
        viewModel = viewModel
    )
}

@Composable
fun MainSetup(
    allDecks: List<Deck>,
    searchResults: List<Deck>,
    viewModel: FlashcardViewModel
) {

}

