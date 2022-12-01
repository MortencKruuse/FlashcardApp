package com.example.flashcardapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashcardapp.data.domain.FlashcardDomain
import com.example.flashcardapp.data.helpers.CardValidator
import com.example.flashcardapp.data.helpers.DeckValidator

class FlashcardViewModel(application: Application) : ViewModel() {
    private val domain: FlashcardDomain
    val allDecks: LiveData<List<Deck>>
    val deckSearchResults: MutableLiveData<List<Deck>>
    val allCards: LiveData<List<Card>>
    val cardSearchResults: MutableLiveData<List<Card>>
    init {

        domain = FlashcardDomain(/*Should not have application*/application)
        allDecks = domain.readAllDeckData
        deckSearchResults = domain.deckSearchResults
        allCards = domain.readAllCardData
        cardSearchResults = domain.cardSearchResults
    }

    fun addDeck(deck: Deck): String {
        if (DeckValidator().ValidateDeck(deck).length < 1) {
            domain.addDeck(deck)
        }
        return DeckValidator().ValidateDeck(deck)
    }

    fun addCard(card: Card): String {
        if (CardValidator().ValidateCard(card).length < 1) {
            domain.addCard(card)
        }
        return CardValidator().ValidateCard(card)
    }

    fun findDeck(deckId: String) {
        domain.findDeck(deckId)
    }

    fun findCard(card: Card) {
        domain.findCard(card.cardId)
    }

    fun deleteDeck(deckId: String) {
        domain.deleteDeck(deckId)
    }

    fun deleteCard(cardId: String) {
        domain.deleteCard(cardId)
    }

    fun updateCard(cardToAdd: Card, cardIdToDelete: Int): String {
        if (CardValidator().ValidateCard(cardToAdd).length < 1) {
            domain.updateCard(cardToAdd, cardIdToDelete)
        }
        return CardValidator().ValidateCard(cardToAdd)
    }
}