package com.example.flashcardapp.ui.selecttopicscreen

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.FlashcardViewModel
import com.example.flashcardapp.data.ViewModelFactory
import com.example.flashcardapp.ui.DTO.DeckDTO
import com.example.flashcardapp.ui.components.Background
import com.example.flashcardapp.ui.components.BackgroundBox
import com.example.flashcardapp.ui.components.DeckTitleRow
import com.example.flashcardapp.ui.components.DemoField

import com.example.flashcardapp.ui.deckscreen.myTopic
import com.example.flashcardapp.ui.deckscreen.resetTextValue
import com.example.flashcardapp.ui.theme.ExtraSquares
import com.example.flashcardapp.ui.theme.TextChangeBubbles
import com.example.flashcardapp.ui.theme.TextColour

@Composable
fun SelectTopicScreen(
    navController: NavController
){

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

        SetUpDeckScreen(
            viewModel,
            navController,
            myTopic,
            onTopicChange = {
                myTopic = it
            })
    }

        }





@Composable
fun SetUpDeckScreen(viewModel: FlashcardViewModel, navController: NavController, topic : String, onTopicChange : (String) -> Unit) {


    val decks by viewModel.getAllDecks().observeAsState(initial = emptyList())

    Column(
        Modifier
            .fillMaxSize()
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth().background(ExtraSquares).height(100.dp)){
            DeckTitleRow(head1 = "", head2 = "Deck Topics")
        }

        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn(
            Modifier
                .padding(8.dp)
                .fillMaxSize()
                .weight(1f)

        ) {



            items(decks) { deck ->

                DeckRow(
                    deckId = deck.deckId,
                    deckTopic = deck.deckTopic,
                    modifier = Modifier,
                    navController = navController
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

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
                .clickable { navController.navigate("flashScreen/$deckId") }
        ) {Column{
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Deck topic" , color = TextColour, fontWeight = FontWeight.Bold)
            Text(text = deckTopic , color = TextColour)
            Spacer(modifier = Modifier.height(15.dp))
        }

        }



    }

}
