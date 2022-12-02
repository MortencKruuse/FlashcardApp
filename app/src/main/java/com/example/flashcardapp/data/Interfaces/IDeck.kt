package com.example.flashcardapp.data.Interfaces

interface IDeck {
    var deckId : String
    var deckTopic : String
    var cards : List<ICard>
}