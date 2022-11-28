package com.example.flashcardapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashcardapp.data.validators.CardValidator
import com.example.flashcardapp.data.validators.DeckValidator

class FlashcardViewModel(application: Application) : ViewModel() {
    private val repository: FlashcardRepository
    val allDecks: LiveData<List<Deck>>
    val deckSearchResults: MutableLiveData<List<Deck>>
    val allCards: LiveData<List<Card>>
    val cardSearchResults: MutableLiveData<List<Card>>


    init {
        val deckDb = FlashcardDatabase.getDatabase(application)
        val deckDao = deckDb.dao()
        repository = FlashcardRepository(deckDao)

        allDecks = repository.readAllDeckData
        deckSearchResults = repository.deckSearchResults
        allCards = repository.readAllCardData
        cardSearchResults = repository.cardSearchResults

    }
    fun addDeck(deck: Deck) : String{
        if (DeckValidator().ValidateDeck(deck).length < 1){
            repository.addDeck(deck)
        }
        return DeckValidator().ValidateDeck(deck)
    }

    fun addCard(card: Card) : String{
        if (CardValidator().ValidateCard(card).length < 1){
            repository.addCard(card)
        }
        return CardValidator().ValidateCard(card)
    }

    fun findDeck(deck: Deck) {
        repository.findDeck(deck.deckId)
    }

    fun findCard(card: Card){
        repository.findCard(card.cardId)
    }

    fun deleteDeck(deckId: Int) {
        repository.deleteDeck(deckId)
    }

    fun deleteCard(cardId: Int) {
        repository.deleteCard(cardId)
    }
}