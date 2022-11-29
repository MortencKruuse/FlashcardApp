package com.example.flashcardapp.ui.validators

import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.FlashcardViewModel

class CardValidator(card : Card, viewModel: FlashcardViewModel)  {


    fun ValidateCard(card: Card, viewModel: FlashcardViewModel): String {

        if (card.question.length < 1 && card.answer.length < 1){
            return "Please type a question and answer before submitting."
        } else if (card.question.length < 1){
            return ""
        } else if (card.answer.length < 1){
            return ""
        } //else if (viewModel.findCard()){
            return ""
        }
}