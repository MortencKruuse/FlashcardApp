package com.example.flashcardapp.data.domain

import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.Deck

interface IFlashcardDomain {

    fun addDeck(deck: Deck)

    fun addCard(card: Card)

    fun deleteDeck(deckId: Int)

    fun deleteCard(cardId: Int)

    fun findDeck(id: Int)

    fun findCard(cardId: Int)


}