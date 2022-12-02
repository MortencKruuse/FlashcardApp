package com.example.flashcardapp.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.Interfaces.ICard
import com.example.flashcardapp.data.Interfaces.IDeck
import com.example.flashcardapp.data.entities.DBCard
import com.example.flashcardapp.data.entities.DBDeck
import com.example.flashcardapp.data.repo.FirebaseDB
import com.example.flashcardapp.data.repo.FlashcardDatabase
import com.example.flashcardapp.ui.DTO.CardDTO
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow


class FlashcardDomain(application : Application) : IFlashcardDomain {
    //Firebase
    private var db = FirebaseDB()
    //Local Room database
    private val DAO =  FlashcardDatabase.getDatabase(application).dao()

    //?? probably should be functions
    /*val readAllDeckData: MutableLiveData<List<IDeck>>()  //= DAO.readAllDecks()
    val deckSearchResults = MutableLiveData<List<IDeck>>()
    val readAllCardData: LiveData<List<ICard>> //= DAO.getAllCards()
    val cardSearchResults = MutableLiveData<List<ICard>>()*/

    //Scopes
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun addDeck(deck: IDeck) {
        val deckDAO = Deck(deck.deckId,deck.deckTopic)
        coroutineScope.launch(Dispatchers.IO) {
            DAO.addDeck(deckDAO)
        }
        coroutineScope.launch(Dispatchers.IO) {
            db.addDeckToFirebase(deck)
        }
    }
    override fun addCard(deckId : String, card: ICard) {
        val cardDAO = Card(card.cardId,card.question,card.answer,deckId)
        coroutineScope.launch(Dispatchers.IO) {
            DAO.addCard(cardDAO)
        }
        coroutineScope.launch(Dispatchers.IO) {
            db.addCardToFirebase(deckId, card)
        }
    }
    override fun deleteDeck(deckId: String) {
        coroutineScope.launch(Dispatchers.IO) {
            DAO.deleteDeck(deckId)
        }
        coroutineScope.launch(Dispatchers.IO) {
            db.deleteDeck(deckId)
        }
    }
    override fun deleteCard(deckId : String, cardId: String) {
        coroutineScope.launch(Dispatchers.IO) {
            DAO.deleteCard(cardId)
        }
        coroutineScope.launch(Dispatchers.IO) {
            db.deleteDeck(deckId)
        }
    }
    fun findDeck(id: String) = channelFlow {
        coroutineScope.launch(Dispatchers.IO) {
            send(db.getDeck(id))
        }
    }



     fun getAllDecks() = channelFlow {
        coroutineScope.launch(Dispatchers.IO) {
            send(db.getDecks())
        }
        awaitClose()
    }
    override fun findCard(cardId: String) {
        coroutineScope.launch(Dispatchers.IO) {
            //cardSearchResults.value = DAO.findCard(cardId)

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
