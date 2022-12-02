package com.example.flashcardapp.domain

import com.example.flashcardapp.data.Interfaces.ICard
import com.example.flashcardapp.data.Interfaces.IDeck

interface IFlashcardDomain {

    fun addDeck(deck: IDeck)

    fun addCard(deckId: String, card: ICard)

    fun deleteDeck(deckId: String)

    fun deleteCard(deckId: String, cardId: String)

    fun findCard(cardId: String)

}