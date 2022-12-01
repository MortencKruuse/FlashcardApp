package com.example.flashcardapp.data

import com.example.flashcardapp.data.Interfaces.IDeck
import com.example.flashcardapp.data.entities.DBDeck


data class Response(
    var decks : List<IDeck>? = null,
    var exception : Exception? = null
)
