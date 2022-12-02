package com.example.flashcardapp.domain.helpers

import com.example.flashcardapp.data.Interfaces.ICard

class CardValidator {
    fun validateCard(card: ICard): String {

        val validCharacters = "[!-~]"

        if (card.question.isEmpty()) {
            return "Please type a question before submitting."
        } else if (card.answer.isEmpty()) {
            return "Please type an answer before submitting."
        } else if (!card.question.contains(validCharacters)) {
            return "Please only use valid characters ($validCharacters) in your question"
        } else if (!card.answer.contains(validCharacters)) {
            return "Please only use valid characters ($validCharacters) in your answer"
        }
        return ""
    }
}