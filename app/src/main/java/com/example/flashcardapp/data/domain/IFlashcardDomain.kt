package com.example.flashcardapp.data.domain

import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.Deck

interface IFlashcardDomain {

    fun addDeck(deck: Deck)

    fun addCard(card: Card)

    fun deleteDeck(deckId: String)

    fun deleteCard(cardId: String)

    fun findDeck(id: String)

    fun findCard(cardId: String)


}