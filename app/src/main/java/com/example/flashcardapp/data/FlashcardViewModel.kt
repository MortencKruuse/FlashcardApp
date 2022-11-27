package com.example.flashcardapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FlashcardViewModel(application: Application) : ViewModel() {
    private val repository: FlashcardRepository
    val allDecks: LiveData<List<Deck>>
    val deckSearchResults: MutableLiveData<List<Deck>>
    val allCards: LiveData<List<Card>>
    val cardSearchResults: MutableLiveData<List<Card>>


    init {
        val deckDb = FlashcardDatabase.getDatabase(application)
        val deckDao = deckDb.dao()
        repository = FlashcardRepository(deckDao)

        allDecks = repository.readAllDeckData
        deckSearchResults = repository.deckSearchResults
        allCards = repository.readAllCardData
        cardSearchResults = repository.cardSearchResults

    }
    fun addCard(card: Card){
        repository.addCard(card)
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