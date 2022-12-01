package com.example.flashcardapp.data.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.Interfaces.ICard
import com.example.flashcardapp.data.Interfaces.IDeck
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
    val readAllDeckData: LiveData<List<Deck>> = DAO.readAllDecks()
    val deckSearchResults = MutableLiveData<List<Deck>>()
    val readAllCardData: LiveData<List<Card>> = DAO.getAllCards()
    val cardSearchResults = MutableLiveData<List<Card>>()

    //Scopes
    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    //Bad name
    /*fun readAllCards() : LiveData<List<Card>>{
        var cards : LiveData<List<Card>>
        coroutineScope.launch(Dispatchers.IO) {
            DAO.getAll()
        }


        return cards
    }*/

    override fun addDeck(deck: IDeck) {
        val deckDAO = Deck(deck.deckId,deck.deckTopic)
        coroutineScope.launch(Dispatchers.IO) {
            DAO.addDeck(deckDAO)
        }
        coroutineScope.launch(Dispatchers.IO) {
            db.addDeckToFirebase(deckDAO)
        }
    }

    override fun addCard(deckId : String, card: ICard) {
        val cardDAO = Card(card.cardId,card.question,card.answer,deckId)
        coroutineScope.launch(Dispatchers.IO) {
            DAO.addCard(cardDAO)
        }
        coroutineScope.launch(Dispatchers.IO) {
            db.addCardToFirebase(cardDAO)
        }
    }

    override fun deleteDeck(deckId: String) {
        coroutineScope.launch(Dispatchers.IO) {
            DAO.deleteDeck(deckId)
        }
    }

    override fun deleteCard(deckId : String, cardId: String) {
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

    fun updateCard(deckId : String, cardToAdd: ICard, cardIdToDelete : String){
        coroutineScope.launch(Dispatchers.IO) {
            DAO.deleteCard(cardIdToDelete)
            val cardDAO = Card(cardToAdd.cardId,cardToAdd.question,cardToAdd.answer,deckId)
            DAO.addCard(cardDAO)
        }
    }
}
