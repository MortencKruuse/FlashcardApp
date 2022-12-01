package com.example.flashcardapp.data.domain

import com.example.flashcardapp.data.Interfaces.ICard
import com.example.flashcardapp.data.Interfaces.IDeck

interface IFlashcardDomain {

    fun addDeck(deck: IDeck)

    fun addCard(deckId : String, card: ICard)

    fun deleteDeck(deckId: String)

    fun deleteCard(deckId :String, cardId: String)

    fun findDeck(id: String)

    fun findCard(cardId: String)

}