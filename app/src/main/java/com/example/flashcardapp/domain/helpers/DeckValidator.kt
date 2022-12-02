package com.example.flashcardapp.domain.helpers

import com.example.flashcardapp.data.Interfaces.IDeck

class DeckValidator {
    fun validateDeck(deck: IDeck): String {
        val validCharacters = "[!-~]"

        return if (deck.deckTopic.isEmpty()) {
            "Please type a topic before submitting a deck."
        } else if (!deck.deckTopic.contains(validCharacters)) {
            "Please only use a valid characters ($validCharacters) in your topic"
        } else ""
    }

}