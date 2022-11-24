package com.example.flashcardapp.repo

import com.example.flashcardapp.data.IDeck
import com.example.flashcardapp.repo.exceptions.RepoException

/**
 * Server:
 */
class DeckHandler : IDeckHandler {
    private val IP = ""

    override fun searchForDeck(keyword: String): IDeck? {
        TODO("Not yet implemented")
        throw RepoException("Not implemented");

    }

    override fun getDeck(id: String): IDeck?{
        TODO("Not yet implemented")
    }
}