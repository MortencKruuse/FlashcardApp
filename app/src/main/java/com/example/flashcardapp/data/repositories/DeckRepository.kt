package com.example.flashcardapp.data.repositories

import androidx.lifecycle.LiveData
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.dao.DeckDAO

class DeckRepository(private val deckDAO: DeckDAO) {
    val readAllData: LiveData<List<Deck>> = deckDAO.readAllDataFromDeck()

    suspend fun addDeck(deck: Deck) {
        deckDAO.addDeck(deck)
    }
}