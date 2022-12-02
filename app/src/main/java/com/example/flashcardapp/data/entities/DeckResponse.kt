package com.example.flashcardapp.data

import com.example.flashcardapp.data.Interfaces.IDeck


data class Response(
    var decks : List<IDeck>? = null,
    var exception : Exception? = null
)

data class SingleResponse(
    var deck : IDeck? = null,
    var exception : Exception? = null
)


