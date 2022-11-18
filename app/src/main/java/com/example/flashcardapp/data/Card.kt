package com.example.flashcardapp.data

import javax.inject.Singleton




class Card : ICard {
    private var instance: Singleton? = null

    private fun Card() {
        var id = null;
        var question = null;
        var answer = null;

    }

    fun getInstance(): Card? {
        if (instance == null) {
            synchronized(Card::class.java) {
                if (instance == null) {
                    instance = Card()
                }
            }
        }
        return instance
    }
    override fun getQuestion(): String {
        TODO("Not yet implemented")
    }

    override fun getAnswer(): String {
        TODO("Not yet implemented")
    }

    override fun updateCard(question: String, answer: String) {

    }

    override fun setCardID(): String {
        TODO("Not yet implemented")
    }

    override fun getCardID(): String {
        TODO("Not yet implemented")
    }
}