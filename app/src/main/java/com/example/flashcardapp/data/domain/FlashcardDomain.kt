package com.example.flashcardapp.data.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.repo.DAO
import com.example.flashcardapp.data.repo.FirebaseDB
import com.example.flashcardapp.data.repo.FlashcardDatabase
import kotlinx.coroutines.*

class FlashcardDomain(application : Application) : IFlashcardDomain {
    //Firebase
    private var db = FirebaseDB()
    //Local Room database
    private val deckDb = FlashcardDatabase.getDatabase(application)
    private val DAO = deckDb.dao()

    //?? probably should be functions
    val readAllDeckData: LiveData<List<Deck>> = DAO.readAllDataFromDeck()
    val deckSearchResults = MutableLiveData<List<Deck>>()
    val readAllCardData: LiveData<List<Card>> = DAO.readAllDataFromCard()
    val cardSearchResults = MutableLiveData<List<Card>>()

    //Scopes
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun addDeck(deck: Deck) {
        coroutineScope.launch(Dispatchers.IO) {
            DAO.addDeck(deck)
        }
        coroutineScope.launch(Dispatchers.IO) {
            db.addDeckToFirebase(deck)
        }
    }

    override fun addCard(card: Card) {
        coroutineScope.launch(Dispatchers.IO) {
            DAO.addCard(card)
        }
        coroutineScope.launch(Dispatchers.IO) {
            db.addCardToFirebase(card)
        }
    }

    override fun deleteDeck(deckId: String) {
        coroutineScope.launch(Dispatchers.IO) {
            DAO.deleteDeck(deckId)
        }
    }

    override fun deleteCard(cardId: String) {
        coroutineScope.launch(Dispatchers.IO) {
            DAO.deleteCard(cardId)
        }
    }

    override fun findDeck(id: String) {
        coroutineScope.launch(Dispatchers.Main) {
            deckSearchResults.value = asyncFindDeck(id).await()
        }
    }

    override fun findCard(cardId: String) {
        coroutineScope.launch(Dispatchers.Main) {
            cardSearchResults.value = asyncFindCard(cardId).await()
        }
    }

    private fun asyncFindDeck(deckId: String): Deferred<List<Deck>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async DAO.findDeck(deckId)
        }

    private fun asyncFindCard(cardId: String): Deferred<List<Card>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async DAO.findCard(cardId)
        }

    fun updateCard(cardToAdd: Card, cardIdToDelete : String){
        coroutineScope.launch(Dispatchers.IO) {
            DAO.deleteCard(cardIdToDelete)
            DAO.addCard(cardToAdd)
        }
    }
}
