package com.example.flashcardapp.data.entities


import com.example.flashcardapp.data.Interfaces.ICard
import com.example.flashcardapp.data.Interfaces.IDeck

class DBDeck : IDeck {
    override var deckId: String = ""
    override var deckTopic: String = ""
    override var cards: List<ICard> = listOf()
}
