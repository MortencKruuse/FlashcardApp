package com.example.flashcardapp.ui.deckscreen

import android.app.Application
import android.widget.Toast
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashcardapp.data.FlashcardViewModel
import com.example.flashcardapp.data.ViewModelFactory
import com.example.flashcardapp.ui.components.Background
import com.example.flashcardapp.ui.components.BackgroundBox


@Composable
fun CardScreen(deckId: Int?, navController: NavController, deckTopic: String?) {
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
                val viewModel: FlashcardViewModel = viewModel(
                    it,
                    "FlashcardViewModel",
                    ViewModelFactory(
                        LocalContext.current.applicationContext
                                as Application
                    )
                )
                SetUpCardScreen(viewModel, navController, deckTopic!!, deckId!!)
            }
        }
    }
}

@Composable
fun SetUpCardScreen(
    viewModel: FlashcardViewModel,
    navController: NavController,
    deckTopic: String,
    deckId: Int
) {

    var question by remember {
        mutableStateOf("")
    }
    var answer by remember {
        mutableStateOf("")
    }
    var topic = deckTopic.toString()

    var deckId = deckId
    /*
    var topic by remember {
        mutableStateOf("")
    }*/
    try {
        if (viewModel.allCards == null) {
            var card = com.example.flashcardapp.data.Card(
                0,
                "Egg White",
                "What is an egg white not?",
                "Yellow."
            )
            viewModel.addCard(card)
            viewModel.deleteCard(card.cardId)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    val allCards by viewModel.allCards.observeAsState(listOf())
    val cardSearchResults by viewModel.cardSearchResults.observeAsState(listOf())

    // Fetching the local context for using the Toast
    val context = LocalContext.current

    Background(alpha = 1f)
    BackgroundBox()


    Column(
        Modifier
            .fillMaxSize()
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .padding(2.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(2.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("DeckID: $deckTopic", textAlign = TextAlign.Center)
            }
        }


        TextFieldWithIconsCard("question", "question your e-mail") {
            question = it
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldWithIconsCard("answer", "Enter your answer") {
            answer = it
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            Toast.makeText(
                context, viewModel.addCard(
                    com.example.flashcardapp.data.Card(
                        0,
                        "$deckTopic ",
                        question,
                        answer
                    )
                ), Toast.LENGTH_LONG
            ).show()
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
                CardTitleRow(head1 = "ID", head2 = "Question")
            }
            val list = allCards

            items(list) { card ->
                CardRow(deckId, deckTopic,card.cardId, card.question, card.answer, navController)
            }
        }
    }
}

@Composable
fun CardTitleRow(head1: String, head2: String) {
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
fun CardRow(deckId: Int, deckTopic: String, cardId: Int, cardQuestion: String, cardAnswer: String, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { navController.navigate("editCardScreen/$deckId/$deckTopic/$cardId/$cardQuestion/$cardAnswer") }
    ) {
        Text(cardId.toString(), modifier = Modifier.weight(0.1f))
        Text(cardQuestion, modifier = Modifier.weight(0.5f))
    }
}


@Composable
fun TextFieldWithIconsCard(label: String, placeholder: String, thingie: (String) -> Unit) {
    return OutlinedTextField(
        value = "",
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        onValueChange = thingie,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
    )
}

