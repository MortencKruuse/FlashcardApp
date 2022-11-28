package com.example.flashcardapp.data.validators

import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.FlashcardViewModel

class CardValidator()  {
    fun ValidateCard(card: Card): String {

        if (card.question.length < 1 && card.answer.length < 1){
            return "Please type a question and answer before submitting."
        } else if (card.question.length < 1){
            return "Please type a question before submitting."
        } else if (card.answer.length < 1){
            return "Please type an answer before submitting."
            //TODO if(viewModel.allCards.contains(card))
        } else if (false){
            return "That card already exists!"
        } else return ""
    }
}