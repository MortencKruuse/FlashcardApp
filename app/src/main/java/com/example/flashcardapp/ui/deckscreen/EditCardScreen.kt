package com.example.flashcardapp.ui.deckscreen

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.FlashcardViewModel
import com.example.flashcardapp.data.ViewModelFactory
import com.example.flashcardapp.ui.components.Background
import com.example.flashcardapp.ui.components.BackgroundBox


@Composable
fun EditCardScreen(
    deckId: Int?,
    deckTopic: String?,
    cardId: Int?,
    navController: NavController,
    cardQuestion: String?,
    cardAnswer: String?
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
                val viewModel: FlashcardViewModel = viewModel(
                    it,
                    "FlashcardViewModel",
                    ViewModelFactory(
                        LocalContext.current.applicationContext
                                as Application
                    )
                )
                SetUpEditCardScreen(
                    deckId!!,
                    deckTopic!!,
                    cardId!!,
                    navController,
                    cardQuestion,
                    cardAnswer,
                    viewModel
                )
            }
        }
    }
}

@Composable
fun SetUpEditCardScreen(
    deckId: Int,
    deckTopic: String,
    cardId: Int,
    navController: NavController,
    cardQuestion: String?,
    cardAnswer: String?,
    viewModel: FlashcardViewModel
) {
    var question by remember {
        mutableStateOf(cardQuestion)
    }

    var answer by remember {
        mutableStateOf(cardAnswer)
    }

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
                Text(
                    "DeckId: " + deckId.toString() + "|" + "On Topic: " + deckTopic,
                    textAlign = TextAlign.Center
                )
            }
        }


        TextFieldWithIconsEditCard("question", cardQuestion.toString()) { question = it }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldWithIconsEditCard("answer", cardAnswer.toString()) { answer = it }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            Toast.makeText(
                context,
                viewModel.updateCard(
                    Card(
                        cardId,
                        deckTopic,
                        question.toString(),
                        answer.toString()
                    ), cardId
                ), Toast.LENGTH_LONG
            ).show()
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Apply changes")
        }

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            //val list = if (searching) searchResults else allProducts

            item {
                EditCardTitleRow(head1 = "Question", head2 = "Answer")
            }

            items(1) { card ->
                EditCardRow(
                    question = question.toString(),
                    answer = answer.toString()
                )
            }
        }
    }
}

@Composable
fun EditCardTitleRow(head1: String, head2: String) {
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
                .weight(0.1f)
        )
    }
}

@Composable
fun EditCardRow(question: String, answer: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(question.toString(), modifier = Modifier.weight(0.1f))
        Text(answer, modifier = Modifier.weight(0.1f))
    }
}


@Composable
fun TextFieldWithIconsEditCard(label: String, placeholder: String, thingie: (String) -> Unit) {
    return OutlinedTextField(
        value = "",
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        onValueChange = thingie,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
    )
}
