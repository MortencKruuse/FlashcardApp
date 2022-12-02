package com.example.flashcardapp.ui.deckscreen

import android.app.Application
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
import com.example.flashcardapp.viewModel.FlashcardViewModel
import com.example.flashcardapp.viewModel.ViewModelFactory
import com.example.flashcardapp.ui.components.Background
import com.example.flashcardapp.ui.components.BackgroundBox


@Composable
fun EditCardScreen(
    deckId: String?

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
                    viewModel
                )
            }
        }
    }
}

@Composable
fun SetUpEditCardScreen(
    deckId: String,
    viewModel: FlashcardViewModel
) {


    val context = LocalContext.current

    Background()
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
                    "DeckId: " + deckId.toString() + "|" + "On Topic: ",
                    textAlign = TextAlign.Center
                )
            }
        }



        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {

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
        Text(question, modifier = Modifier.weight(0.1f))
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
