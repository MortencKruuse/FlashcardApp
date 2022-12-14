package com.example.flashcardapp.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.Interfaces.ICard
import com.example.flashcardapp.data.Interfaces.IDeck
import com.example.flashcardapp.data.domain.FlashcardDomain
import com.example.flashcardapp.data.domain.helpers.CardValidator
import com.example.flashcardapp.data.domain.helpers.DeckValidator

class FlashcardViewModel(application: Application) : ViewModel() {
    private val domain: FlashcardDomain
    val allDecks: LiveData<List<Deck>>
    val deckSearchResults: MutableLiveData<List<Deck>>
    val allCards: LiveData<List<Card>>
    val cardSearchResults: MutableLiveData<List<ICard>>

    init {
        domain = FlashcardDomain(application)
        allDecks = domain.readAllDeckData
        deckSearchResults = domain.deckSearchResults
        allCards = domain.readAllCardData
        cardSearchResults = domain.cardSearchResults
    }

    fun addDeck(deck: IDeck): String {
        if (DeckValidator().ValidateDeck(deck).isEmpty()) {
            domain.addDeck(deck)
        }
        return DeckValidator().ValidateDeck(deck)
    }

    fun addCard(deckId: String, card: ICard): String {
        if (CardValidator().ValidateCard(card).isEmpty()) {
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
        if (CardValidator().ValidateCard(cardToAdd).isEmpty()) {
            domain.updateCard(deckId, cardToAdd, cardIdToDelete)
        }
        return CardValidator().ValidateCard(cardToAdd)
    }
}