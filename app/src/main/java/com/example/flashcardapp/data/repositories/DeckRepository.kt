package com.example.flashcardapp.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.dao.DeckDAO
import kotlinx.coroutines.*

class DeckRepository(private val deckDAO: DeckDAO) {
    val readAllData = MutableLiveData<List<Deck>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    fun addDeck (deck: Deck) {
        coroutineScope.launch(Dispatchers.IO) {
            deckDAO.addDeck(deck)
        }
    }

    fun deleteDeck(deck: Deck) {
        coroutineScope.launch(Dispatchers.IO) {
            deckDAO.deleteDeck(deck)
        }
    }

    fun findDeck(id: Int) {
        coroutineScope.launch(Dispatchers.Main) {
            readAllData.value = asyncFind(id).await()
        }
    }

    private fun asyncFind(id: Int): Deferred<List<Deck>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async deckDAO.findDeck(id)
        }
}
