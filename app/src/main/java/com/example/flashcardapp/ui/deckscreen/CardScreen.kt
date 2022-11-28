package com.example.flashcardapp.ui.deckscreen

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.FlashcardViewModel
import com.example.flashcardapp.data.ViewModelFactory


@Preview
@Composable
fun CardScreen() {
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
                SetUpCardScreen(viewModel)
            }
        }
    }
}

@Composable
fun SetUpCardScreen(viewModel: FlashcardViewModel) {
    var question by remember {
        mutableStateOf("")
    }
    var answer by remember {
        mutableStateOf("")
    }
    var topic by remember {
        mutableStateOf("")
    }
    try {
        if (viewModel.allCards == null) {
            var card = Card(0, "Egg White", "What is an egg white not?", "Yellow.")
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

                //TODO change to take input from viewModel
                //topic = it
                Text("Topic", textAlign = TextAlign.Center)
            }
        }


        TextFieldWithIconsCard("question", "question your e-mail") { question = it }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldWithIconsCard("answer", "Enter your answer") { answer = it }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            //TODO addCardTopic from decktopic
            //viewModel.addCard(Card(0, "Egg White", question, answer))
            Toast.makeText(context, viewModel.addCard(Card(0, "Egg White", question, answer)), Toast.LENGTH_LONG).show()
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Submit")
        }

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            //val list = if (searching) deckSearchResults else allProducts

            item {
                CardTitleRow(head1 = "ID", head2 = "Question", "Answer")
            }
            val list = allCards

            items(list) { card ->
                CardRow(id = card.cardId, card.question, card.answer)
            }
        }
    }
}

@Composable
fun CardTitleRow(head1: String, head2: String, head3: String) {
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
        Text(
            head3, color = Color.White,
            modifier = Modifier
                .weight(0.5f)
        )
    }
}

@Composable
fun CardRow(id: Int, question: String, answer: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(id.toString(), modifier = Modifier.weight(0.1f))
        Text(question, modifier = Modifier.weight(0.5f))
        Text(answer, modifier = Modifier.weight(0.5f))
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

