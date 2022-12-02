package com.example.flashcardapp.data

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.flashcardapp.data.Interfaces.ICard
import com.example.flashcardapp.data.Interfaces.IDeck
import com.example.flashcardapp.domain.FlashcardDomain
import com.example.flashcardapp.domain.helpers.CardValidator
import com.example.flashcardapp.domain.helpers.DeckValidator
import kotlinx.coroutines.Dispatchers

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
            emit(response)
        }
    }

    fun getAllCards() = liveData(Dispatchers.IO) {
        domain.getAllCards().collect() { response ->
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