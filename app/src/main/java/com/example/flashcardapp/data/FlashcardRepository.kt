package com.example.flashcardapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class FlashcardRepository(private val DAO: DAO) {
    val readAllData: LiveData<List<Deck>> = DAO.readAllDataFromDeck()
    val searchResults = MutableLiveData<List<Deck>>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    fun addDeck (deck: Deck) {
        coroutineScope.launch(Dispatchers.IO) {
            DAO.addDeck(deck)
        }
    }

    fun deleteDeck(deckId: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            DAO.deleteDeck(deckId)
        }
    }

    fun findDeck(id: Int) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(id).await()
        }
    }

    private fun asyncFind(id: Int): Deferred<List<Deck>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async DAO.findDeck(id)
        }
}
