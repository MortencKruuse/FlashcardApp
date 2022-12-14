package com.example.flashcardapp.ui.deckscreen


import android.app.Application
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Topic
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashcardapp.viewModel.FlashcardViewModel
import com.example.flashcardapp.viewModel.ViewModelFactory
import com.example.flashcardapp.ui.DTO.DeckDTO
import com.example.flashcardapp.ui.components.Background
import com.example.flashcardapp.ui.components.BackgroundBox
import com.example.flashcardapp.ui.components.DeckTitleRow
import com.example.flashcardapp.ui.components.DemoField
import com.example.flashcardapp.ui.theme.*
var topic = mutableStateOf("")
var myTopic by topic

@Composable
fun DeckScreen(
    navController: NavController
) {
    Background()
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

        SetUpDeckScreen(viewModel, navController, myTopic, onTopicChange = {
            myTopic = it
        })
    }
}


//Source: https://stackoverflow.com/a/54400933
private fun generateID(length : Int) : String{
    //Allowed chars
    val allowedChars = ('A'.. 'Z') + ('a' .. 'z') + (0 .. 9)
    //Return string from size 1 to length (means smallest is 1)
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}


@Composable
fun SetUpDeckScreen(viewModel: FlashcardViewModel, navController: NavController, topic : String, onTopicChange : (String) -> Unit) {

    val allDecks by viewModel.allDecks.observeAsState(listOf())
    val searchResults by viewModel.deckSearchResults.observeAsState(listOf())



    Column(
        Modifier
            .fillMaxSize()
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth().background(ExtraSquares).height(100.dp)){
            DeckTitleRow(head1 = "Create your own deck or select one", head2 = "Deck Topics")
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
            border = BorderStroke(1.dp, ExtraSquares),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = ExtraSquares),
            onClick = {
                viewModel.addDeck(DeckDTO(generateID(16),topic))
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
fun DeckRow(deckId: String, deckTopic: String, modifier: Modifier, navController: NavController) {
    Box(){

        Row(
            modifier
                .clip(shape = RoundedCornerShape(25))
                .background(TextChangeBubbles)
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(8.dp)
                .clickable { navController.navigate("cardScreen/$deckId/$deckTopic") }
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

fun resetTextValue(){
    topic.value = ""
}
