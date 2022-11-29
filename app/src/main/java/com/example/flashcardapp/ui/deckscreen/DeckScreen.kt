package com.example.flashcardapp.ui.deckscreen


import android.app.Application
import android.text.TextUtils.isEmpty
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.db.DeckDatabase
import com.example.flashcardapp.data.repositories.DeckRepository
import com.example.flashcardapp.data.viewmodels.DeckViewModel
import com.example.flashcardapp.data.viewmodels.ViewModelFactory

import com.example.flashcardapp.ui.mainscreen.MainScreen
import com.example.flashcardapp.ui.theme.FlashcardAppTheme

@Composable
fun DeckScreen(
    //onNavigateToCard: () -> Unit
    navController: NavController
) {


    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {

            val owner = LocalViewModelStoreOwner.current

            owner?.let {
                val viewModel: DeckViewModel = viewModel(
                    it,
                    "DeckViewModel",
                    ViewModelFactory(
                        LocalContext.current.applicationContext
                                as Application
                    )
                )

                SetUpDeckScreen(viewModel)
            }
        }
    }
}

@Composable
fun SetUpDeckScreen(viewModel: DeckViewModel) {
    var topic by remember() {
        mutableStateOf("")
    }
    try {
        if (viewModel.allDecks == null) {
            var deck = Deck(0, "No topic given")
            viewModel.addDeck(deck)
            viewModel.deleteDeck(deck.deckId)
        }
    } catch (e: Exception){
        e.printStackTrace()
    }

    val allDecks by viewModel.allDecks.observeAsState(listOf())
    val searchResults by viewModel.searchResults.observeAsState(listOf())

    Column(
        Modifier
            .fillMaxSize()
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldWithIconsDeck("Topic", "Enter your deck topic here")
        {
            topic = it
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            viewModel.addDeck(Deck(0, topic))
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Submit")
        }

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            //val list = if (searching) searchResults else allProducts

            item {
                DeckTitleRow(head1 = "ID", head2 = "Deck Topic")
            }
            val list = allDecks


            items(list) { deck ->
                DeckRow(id = deck.deckId, name = deck.deckTopic, modifier = Modifier)
            }
        }
    }
}

@Composable
fun DeckTitleRow(head1: String, head2: String) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            head1, color = Color.White,
            modifier = Modifier
                .weight(0.1f)
        )
        Text(
            head2, color = Color.White,
            modifier = Modifier
                .weight(0.5f)
        )
    }
}


@Composable
fun DeckRow(id: Int, name: String, modifier: Modifier,navController: NavController) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { navController.navigate("cardScreen/$id") },
    ) {
        Text(
            id.toString(), modifier = Modifier
                .weight(0.1f)
        )
        Text(name, modifier = Modifier.weight(0.5f))
    }
}


@Composable
fun TextFieldWithIconsDeck(label: String, placeholder: String, thingie: (String) -> Unit) {
    return OutlinedTextField(
        value = "",
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        onValueChange = thingie,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) }
    )
}

