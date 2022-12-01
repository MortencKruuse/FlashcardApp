package com.example.flashcardapp.ui.deckscreen


import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.FlashcardViewModel
import com.example.flashcardapp.data.ViewModelFactory
import com.example.flashcardapp.ui.DTO.DeckDTO
import com.example.flashcardapp.ui.components.Background
import com.example.flashcardapp.ui.components.BackgroundBox

@Composable
fun DeckScreen(
    //onNavigateToCard: () -> Unit
    navController: NavController
) {
    Background(1f)
    BackgroundBox()

    val owner = LocalViewModelStoreOwner.current

    owner?.let {
        val viewModel: FlashcardViewModel = viewModel(
            it,
            "DeckViewModel",
            ViewModelFactory(
                LocalContext.current.applicationContext
                        as Application
            )
        )

        SetUpDeckScreen(viewModel, navController)
    }
}


//Source: https://stackoverflow.com/a/54400933
private fun generateID(length : Int) : String{
    //Allowed chars
    val allowedChars = ('A'.. 'Z') + ('a' .. 'z') + (0 .. 9)
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}


@Composable
fun SetUpDeckScreen(viewModel: FlashcardViewModel, navController: NavController) {
    var topic by remember {
        mutableStateOf("")
    }

    val allDecks by viewModel.allDecks.observeAsState(listOf())
    val searchResults by viewModel.deckSearchResults.observeAsState(listOf())

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
            //@TODO UNIQUE ID
            viewModel.addDeck(DeckDTO(generateID(16), topic))
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
                DeckRow(deckId = deck.deckId, deckTopic = deck.deckTopic, modifier = Modifier, navController = navController)
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
fun DeckRow(deckId: String, deckTopic: String, modifier: Modifier, navController: NavController) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { navController.navigate("cardScreen/$deckId/$deckTopic") },
    ) {
        Text(
            deckId.toString(), modifier = Modifier
                .weight(0.1f)
        )
        Text(deckTopic, modifier = Modifier.weight(0.5f))
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

