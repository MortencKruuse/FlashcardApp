package com.example.flashcardapp.data.domain.helpers

import com.example.flashcardapp.data.Interfaces.IDeck

class DeckValidator {
    fun ValidateDeck(deck: IDeck): String {

        if (deck.deckTopic.length < 1) {
            return "Please type a topic before submitting a deck."
        }
        //TODO if(viewModel.allDecks.contains(deck))
        else if (false) {
            return "That deck already exists!"
        } else return ""
    }
}