package com.example.flashcardapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FlashcardViewModel(application: Application) : ViewModel() {
    val allDecks: LiveData<List<Deck>>
    private val repository: FlashcardRepository
    val searchResults: MutableLiveData<List<Deck>>

    init {
        val deckDb = FlashcardDatabase.getDatabase(application)
        val deckDao = deckDb.deckDAO()
        repository = FlashcardRepository(deckDao)

        allDecks = repository.readAllData
        searchResults = repository.searchResults
    }
    fun addDeck(deck: Deck) {
        repository.addDeck(deck)
    }

    fun findDeck(deck: Deck) {
        repository.findDeck(deck.deckId)
    }

    fun deleteDeck(deckId: Int) {
        repository.deleteDeck(deckId)
    }
}