package com.example.flashcardapp.data

import android.annotation.SuppressLint

class Card : ICard {

    @SuppressLint("NotConstructor")
    private fun Card(idX: String, questionX: String, answerX: String) {
        val id: String = idX
        val question: String = questionX
        val answer: String = answerX
    }


    override fun getQuestion(cardId: String): String {
        TODO("Not yet implemented")
    }

    override fun getAnswer(cardId: String): String {
        TODO("Not yet implemented")
    }

    override fun updateCard(cardId: String, question: String, answer: String) {
        TODO("Not yet implemented")
    }

    override fun setCardID(): String {
        TODO("Not yet implemented")
    }

    override fun getCardID(): String {
        TODO("Not yet implemented")
    }
}