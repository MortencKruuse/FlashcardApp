package com.example.flashcardapp.ui.DTO

import com.example.flashcardapp.data.Interfaces.IDeck

class DeckDTO(
    override var deckId: String,
    override var deckTopic: String
    ) : IDeck {
}