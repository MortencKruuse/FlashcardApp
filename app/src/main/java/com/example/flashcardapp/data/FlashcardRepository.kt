package com.example.flashcardapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class FlashcardRepository(private val DAO: DAO) {
    val readAllDeckData: LiveData<List<Deck>> = DAO.readAllDataFromDeck()
    val deckSearchResults = MutableLiveData<List<Deck>>()
    val readAllCardData: LiveData<List<Card>> = DAO.readAllDataFromCard()
    val cardSearchResults = MutableLiveData<List<Card>>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    fun addDeck (deck: Deck) {
        coroutineScope.launch(Dispatchers.IO) {
            DAO.addDeck(deck)
        }
    }

    fun addCard(card: Card) {
        coroutineScope.launch(Dispatchers.IO) {
            DAO.addCard(card)
        }
    }

    fun deleteDeck(deckId: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            DAO.deleteDeck(deckId)
        }
    }

    fun findDeck(id: Int) {
        coroutineScope.launch(Dispatchers.Main) {
            deckSearchResults.value = asyncFind(id).await()
        }
    }

    private fun asyncFind(id: Int): Deferred<List<Deck>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async DAO.findDeck(id)
        }
}
