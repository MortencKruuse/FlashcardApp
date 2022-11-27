package com.example.flashcardapp.data.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.db.DeckDatabase
import com.example.flashcardapp.data.repositories.DeckRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeckViewModel(application: Application) : ViewModel() {
    val allDecks: LiveData<List<Deck>>
    private val repository: DeckRepository
    val searchResults: MutableLiveData<List<Deck>>

    init {
        val deckDb = DeckDatabase.getDatabase(application)
        val deckDao = deckDb.deckDAO()
        repository = DeckRepository(deckDao)

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