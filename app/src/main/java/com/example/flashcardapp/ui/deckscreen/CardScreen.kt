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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashcardapp.data.FlashcardViewModel
import com.example.flashcardapp.data.ViewModelFactory
import com.example.flashcardapp.ui.DTO.CardDTO
import com.example.flashcardapp.ui.DTO.DeckDTO
import com.example.flashcardapp.ui.components.Background
import com.example.flashcardapp.ui.components.BackgroundBox
import com.example.flashcardapp.ui.components.DeckTitleRow
import com.example.flashcardapp.ui.components.DemoField
import com.example.flashcardapp.ui.theme.*

var question = mutableStateOf("")

var answer = mutableStateOf("")

var myQuestion by question

var myAnswer by answer
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
                SetUpCardScreen(viewModel, navController, deckTopic!!, deckId!!, myQuestion, onQuestionChange = {
                    myQuestion = it
                },
                    myAnswer, onAnswerChange = {
                        myAnswer = it
                    } )
            }
        }
    }
}

@Composable
fun SetUpCardScreen(
    viewModel: FlashcardViewModel,
    navController: NavController,
    deckTopic: String,
    deckId: String,
    question : String, onQuestionChange : (String) -> Unit,
    answer : String, onAnswerChange : (String) -> Unit
) {


    var topic = deckTopic.toString()

    var deckId = deckId





    // Fetching the local context for using the Toast
    val context = LocalContext.current

    Background()
    BackgroundBox()


    Column(
        Modifier
            .fillMaxSize()
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth().background(ExtraSquares).height(100.dp)){
            DeckTitleRow(head1 = "Create your own card or edit one", head2 = "Cards")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            Modifier
                .padding(14.dp)
                .fillMaxSize()
                .weight(1f)
        ) {
            //val list = if (searching) searchResults else allProducts

            item {

            }
           // val list = allCards

         //   items(list) { card ->
         //       CardRow(deckId, deckTopic,card.cardId, card.question, card.answer, navController)
        //    }
        }
        DemoField(question,"Question", "Enter your question", onValueChange = onQuestionChange , leadingIcon = {
            Icon(Icons.Default.QuestionAnswer, contentDescription = "Question")
        })
        Spacer(modifier = Modifier.height(4.dp))
        DemoField(answer,"Answer" ,"Enter your answer", onValueChange = onAnswerChange,
        leadingIcon = {
            Icon(Icons.Default.Check,"Answer")
        })
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            border = BorderStroke(1.dp, ExtraSquares),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = ExtraSquares),
            onClick = {

                resetTextValue()

            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        ) {
            Text(text = "Submit")
        }


    }
}


@Composable
fun CardRow(deckId: String, deckTopic: String, cardId: String, cardQuestion: String, cardAnswer: String, navController: NavController) {
    Box(){

        Row(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(25))
                .background(TextChangeBubbles)
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(8.dp)
                .clickable { navController.navigate("editCardScreen/$deckId") }
        ) {Column{
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Deck topic" , color = TextColour, fontWeight = FontWeight.Bold)
            Text(text = deckTopic , color = TextColour)
            Divider(color = Color.Black,thickness = 1.dp)
            Text(text = "Amount of cards in deck 4", color = TextColour)
            Spacer(modifier = Modifier.height(15.dp))
        }

        }



    }
}

fun resetTextValueCard(){
    question.value = ""
    answer.value = ""
}



