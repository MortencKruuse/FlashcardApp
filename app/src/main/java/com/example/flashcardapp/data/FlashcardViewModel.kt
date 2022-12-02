package com.example.flashcardapp.data

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log

import androidx.lifecycle.ViewModel
import com.example.flashcardapp.data.Interfaces.ICard
import com.example.flashcardapp.data.Interfaces.IDeck
import com.example.flashcardapp.domain.FlashcardDomain
import com.example.flashcardapp.domain.helpers.CardValidator
import com.example.flashcardapp.domain.helpers.DeckValidator
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.liveData

class FlashcardViewModel(application: Application) : ViewModel() {
    private val domain: FlashcardDomain


    init {
        domain = FlashcardDomain(application)
    }

    fun addDeck(deck: IDeck): String {
        if (DeckValidator().validateDeck(deck).isEmpty()) {
            domain.addDeck(deck)
        }
        return DeckValidator().validateDeck(deck)
    }

    fun addCard(deckId: String, card: ICard): String {
        if (CardValidator().validateCard(card).isEmpty()) {
            domain.addCard(deckId, card)
        }
        return CardValidator().validateCard(card)
    }

    fun getAllDecks() = liveData(Dispatchers.IO) {
        domain.getAllDecks().collect() { response ->
            //@TODO DELETE
            Log.e(TAG,"HEJSA" + response.toString())
            emit(response)
        }
    }

    fun findDeck(deckId: String) {
        domain.findDeck(deckId)
    }

    fun findCard(card: ICard) {
        domain.findCard(card.cardId)
    }

    fun deleteDeck(deckId: String) {
        domain.deleteDeck(deckId)
    }

    fun deleteCard(deckId: String, cardId: String) {
        domain.deleteCard(deckId, cardId)
    }

    fun updateCard(deckId: String, cardToAdd: ICard, cardIdToDelete: String): String {
        if (CardValidator().validateCard(cardToAdd).isEmpty()) {
            domain.updateCard(deckId, cardToAdd, cardIdToDelete)
        }
        return CardValidator().validateCard(cardToAdd)
    }
}