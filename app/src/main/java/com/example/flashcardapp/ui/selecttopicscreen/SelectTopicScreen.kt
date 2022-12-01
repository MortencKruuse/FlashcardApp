package com.example.flashcardapp.ui.selecttopicscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.ui.components.Background
import com.example.flashcardapp.ui.components.BackgroundBox
import com.example.flashcardapp.ui.components.DeckTitleRow

@Composable
fun SelectTopicScreen(){

    Background(1f)
    BackgroundBox()

    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(48.dp)
    ) {
        //val list = if (searching) searchResults else allProducts

        item {
            DeckTitleRow(head1 = "ID", head2 = "Deck Topic")
        }
      /*  val list = List<Deck>()
        items(list) { deck ->
            DeckRow(deckId = deck.deckId, deckTopic = deck.deckTopic, modifier = Modifier, navController = navController)
        }
        */

        }
    }


