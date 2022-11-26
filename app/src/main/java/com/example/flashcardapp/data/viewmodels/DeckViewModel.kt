package com.example.flashcardapp.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.db.DeckDatabase
import com.example.flashcardapp.data.repositories.DeckRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeckViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData: LiveData<List<Deck>>
    private val repository: DeckRepository

    init {
        val deckDAO = DeckDatabase.getDatabase(application).deckDAO()
        repository = DeckRepository(deckDAO)
        readAllData = repository.readAllData
    }

    fun addDeck(deck: Deck) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDeck(deck)
        }
    }
}