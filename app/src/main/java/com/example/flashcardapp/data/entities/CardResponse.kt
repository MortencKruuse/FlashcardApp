package com.example.flashcardapp.data.entities

import com.example.flashcardapp.data.Interfaces.ICard

data class CardResponse (
        var cards : List<ICard>? = null,
        var exception : Exception? = null
    )
