package com.example.flashcardapp.ui.deckscreen

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashcardapp.data.FlashcardViewModel
import com.example.flashcardapp.data.ViewModelFactory
import com.example.flashcardapp.ui.DTO.CardDTO
import com.example.flashcardapp.ui.components.Background
import com.example.flashcardapp.ui.components.BackgroundBox
import com.example.flashcardapp.ui.components.DemoField
import com.example.flashcardapp.ui.theme.Purple200
import com.example.flashcardapp.ui.theme.Purple500


@Composable
fun CardScreen(deckId: String?, navController: NavController, deckTopic: String?) {
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
    deckId: String
) {

    var question by remember {
        mutableStateOf("")
    }
    var answer by remember {
        mutableStateOf("")
    }
    var topic = deckTopic.toString()

    var deckId = deckId

    if (viewModel.allCards == null) {
            var card = CardDTO(
                "0",
                "Egg White",
                "What is an egg white not?",
            )
            viewModel.addCard("0",card)
            viewModel.deleteCard("0",card.cardId)
        }




    val allCards by viewModel.allCards.observeAsState(listOf())
    val cardSearchResults by viewModel.cardSearchResults.observeAsState(listOf())

    // Fetching the local context for using the Toast
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
                Text("DeckID: $deckTopic", textAlign = TextAlign.Center)
            }
        }


        DemoField(question,"Question", "Enter your question", onValueChange = {
            question = it
        }, leadingIcon = {
            Icon(Icons.Default.QuestionAnswer, contentDescription = "Question")
        })
        Spacer(modifier = Modifier.height(8.dp))
        DemoField(answer,"Answer" ,"Enter your answer", onValueChange = {
                answer = it
        },
        leadingIcon = {
            Icon(Icons.Default.Check,"Answer")
        })
        Spacer(modifier = Modifier.height(8.dp))
        Button(border = BorderStroke(1.dp, Purple200),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Purple200),
            onClick = {
            Toast.makeText(
                context, viewModel.addCard(deckId,
                    CardDTO(
                        "0",
                        question,
                        answer
                    )
                ), Toast.LENGTH_LONG
            ).show()
        }, modifier = Modifier.fillMaxWidth()
                .padding(8.dp)) {
            Text(text = "Submit")
        }

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            //val list = if (searching) searchResults else allProducts

            item {
                CardTitleRow(head1 = "ID", head2 = "Question", head3 = "Answer")
            }
            val list = allCards

            items(list) { card ->
                CardRow(deckId, deckTopic,card.cardId, card.question, card.answer, navController)
            }
        }
    }
}

@Composable
fun CardTitleRow(head1: String, head2: String, head3: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Purple500))
            .clip(RoundedCornerShape(50))

    ) {
        Text(
            head1, color = Purple200,
            modifier = Modifier
                .weight(0.1f)
        )
        Text(
            head2, color = Purple200,
            modifier = Modifier
                .weight(0.5f)
        )
        Text(
            head3, color = Purple200,
            modifier = Modifier
                .weight(0.5f)
        )
    }
}

@Composable
fun CardRow(deckId: String, deckTopic: String, cardId: String, cardQuestion: String, cardAnswer: String, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Purple500))
            .clickable { navController.navigate("editCardScreen/$deckId/$deckTopic/$cardId/$cardQuestion/$cardAnswer") }
    ) {
        Text(cardId.toString(), modifier = Modifier.weight(0.1f))
        Text(cardQuestion, modifier = Modifier.weight(0.5f))
        Text(cardAnswer, modifier = Modifier.weight(0.4f))
    }
}



