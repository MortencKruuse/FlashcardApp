package com.example.flashcardapp.ui.DTO

import com.example.flashcardapp.data.Interfaces.ICard

class CardDTO(
    override var cardId: String,
    override var question: String,
    override var answer: String
) : ICard {

}