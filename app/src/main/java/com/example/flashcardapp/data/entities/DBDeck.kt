package com.example.flashcardapp.data.entities


import com.example.flashcardapp.data.Interfaces.ICard
import com.example.flashcardapp.data.Interfaces.IDeck

class DBDeck : IDeck {
    override var deckId: String = ""
    override var deckTopic: String = ""
    override var cards: List<ICard> = listOf()

    override fun toString(): String {
        var str = ""
        str += "deckId=$deckId\n"
        str += "deckTopic=$deckTopic\n"
        for (ICard in cards) {
            str += cards.toString()
        }
        return str
    }
}
