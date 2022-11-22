package com.example.flashcardapp.data

data class Card(override var cardId: String,override var question: String,override var answer: String) : ICard {

    override fun getQuestion(cardId: String): String {
        return question
    }

    override fun getAnswer(cardId: String): String {
        return answer
    }

    override fun updateCard(cardIdToUpdate: String, questionToUpdate: String, answerToUpdate: String) {
        if (cardIdToUpdate == cardId){
            question = questionToUpdate
            answer = answerToUpdate
        } else return
    }

    override fun setCardID(formerCardIdToUpdate: String, cardIdToUpdate: String) {
        if (formerCardIdToUpdate == cardId){
            cardId = cardIdToUpdate
        } else return
    }
}
