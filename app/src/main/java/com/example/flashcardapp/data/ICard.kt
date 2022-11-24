package com.example.flashcardapp.data

/**
 * I card
 *
 * @constructor Create empty I card
 */
//TODO Who has the responsibility of checking if a card is valid
interface ICard {
    var cardId: String
    var question: String
    var answer: String
    fun getQuestion(cardId : String) : String
    fun getAnswer(cardId : String) : String
    fun updateCard(cardId : String, question : String, answer : String)
    fun setCardID(formerCardIdToUpdate: String, cardIdToUpdate: String)
}