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


    fun addDeck(deck: Deck) {
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

    fun deleteCard(cardId: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            DAO.deleteCard(cardId)
        }
    }

    fun findDeck(id: Int) {
        coroutineScope.launch(Dispatchers.Main) {
            deckSearchResults.value = asyncFindDeck(id).await()
        }
    }

    fun findCard(cardId: Int) {
        coroutineScope.launch(Dispatchers.Main) {
            cardSearchResults.value = asyncFindCard(cardId).await()
        }
    }

    private fun asyncFindDeck(deckId: Int): Deferred<List<Deck>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async DAO.findDeck(deckId)
        }

    private fun asyncFindCard(cardId: Int): Deferred<List<Card>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async DAO.findCard(cardId)
        }
}
