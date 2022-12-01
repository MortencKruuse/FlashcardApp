package com.example.flashcardapp.ui.deckscreen


import android.app.Application
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Topic
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.FlashcardViewModel
import com.example.flashcardapp.data.ViewModelFactory
import com.example.flashcardapp.ui.components.Background
import com.example.flashcardapp.ui.components.BackgroundBox
import com.example.flashcardapp.ui.components.DeckTitleRow
import com.example.flashcardapp.ui.components.DemoField
import com.example.flashcardapp.ui.theme.*


@Composable
fun DeckScreen(
    navController: NavController
) {
    Background(1f)
    BackgroundBox()
    var topic by remember{
        mutableStateOf("")
    }
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

        SetUpDeckScreen(viewModel, navController, topic, onTopicChange = {
            topic = it
        })
    }
}

@Composable
fun SetUpDeckScreen(viewModel: FlashcardViewModel, navController: NavController, topic: String, onTopicChange: (String) -> Unit,) {
    try {
        if (viewModel.allDecks == null) {
            var deck = Deck(0, "No topic given")
            viewModel.addDeck(deck)
            viewModel.deleteDeck(deck.deckId)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    val allDecks by viewModel.allDecks.observeAsState(listOf())
    val searchResults by viewModel.deckSearchResults.observeAsState(listOf())

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(8.dp))


        LazyColumn(
            Modifier
                .fillMaxSize()
                .weight(1f)

        ) {
            //val list = if (searching) searchResults else allProducts
            item {
                DeckTitleRow(head1 = "ID", head2 = "Deck Topics")

            }
            val list = allDecks


            items(list) { deck ->

                DeckRow(
                    deckId = deck.deckId,
                    deckTopic = deck.deckTopic,
                    modifier = Modifier,
                    navController = navController
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

        }

        DemoField(value = topic, label = "Create your own deck with a topic", placeholder = "Enter your topic", onValueChange = onTopicChange, leadingIcon = {
            Icon(Icons.Default.Topic, contentDescription = "Topic")
        } )
        Button(
            border = BorderStroke(1.dp, Purple500),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Purple500),
            onClick = {
                viewModel.addDeck(Deck(0, topic))
            }, modifier = Modifier
                .fillMaxWidth()

        ) {
            Text(text = "Submit")
        }
    }
}


@Composable
fun DeckRow(deckId: Int, deckTopic: String, modifier: Modifier, navController: NavController) {
    Box(){




        Row(
            modifier
                .clip(shape = RoundedCornerShape(20))
                .background(ForestGreenLight)
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { navController.navigate("cardScreen/$deckId/$deckTopic") }
        ) {Column{
            Text(text = "Deck topic" , color = Purple200, fontWeight = FontWeight.Bold)
            Text(text = deckTopic , color = Color.White)
            Divider(color = Color.Companion.Black,thickness = 1.dp)
            Text(text = "Amount of cards in deck 4", color = Color.White )
        }

        }



    }

}




