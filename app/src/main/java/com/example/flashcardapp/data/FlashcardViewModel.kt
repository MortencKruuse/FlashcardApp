package com.example.flashcardapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashcardapp.data.Interfaces.ICard
import com.example.flashcardapp.data.Interfaces.IDeck
import com.example.flashcardapp.domain.FlashcardDomain
import com.example.flashcardapp.domain.helpers.CardValidator
import com.example.flashcardapp.domain.helpers.DeckValidator

class FlashcardViewModel(application: Application) : ViewModel() {
    private val domain: FlashcardDomain
    val allDecks: LiveData<List<Deck>>
    val deckSearchResults: MutableLiveData<List<Deck>>
    val allCards: LiveData<List<Card>>
    val cardSearchResults: MutableLiveData<List<Card>>

    init {
        domain = FlashcardDomain(application)
        allDecks = domain.readAllDeckData
        deckSearchResults = domain.deckSearchResults
        allCards = domain.readAllCardData
        cardSearchResults = domain.cardSearchResults
    }

    fun addDeck(deck: IDeck): String {
        if (DeckValidator().ValidateDeck(deck).length < 1) {
            domain.addDeck(deck)
        }
        return DeckValidator().ValidateDeck(deck)
    }

    fun addCard(deckId: String, card: ICard): String {
        if (CardValidator().ValidateCard(card).length < 1) {
            domain.addCard(deckId, card)
        }
        return CardValidator().ValidateCard(card)
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
        if (CardValidator().ValidateCard(cardToAdd).length < 1) {
            domain.updateCard(deckId, cardToAdd, cardIdToDelete)
        }
        return CardValidator().ValidateCard(cardToAdd)
    }
}