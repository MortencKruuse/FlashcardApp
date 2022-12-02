package com.example.flashcardapp.data.entities

import com.example.flashcardapp.data.Interfaces.ICard

class DBCard : ICard {
    override var cardId: String = ""

    override var question: String = ""

    override var answer: String = ""

    override fun toString() : String{
        var str : String = ""
        str += "cardId=$cardId"
        str += "\nquestion=$question"
        str += "\nanswer=$answer"
        return str
    }
}