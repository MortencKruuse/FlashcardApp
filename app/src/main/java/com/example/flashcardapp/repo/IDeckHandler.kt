package com.example.flashcardapp.repo

import com.example.flashcardapp.data.IDeck

/**
 * I deck handler
 *
 * @constructor Create empty I deck handler
 */
interface IDeckHandler {
    /**
     * Get deck
     *
     * @param id
     * @return
     */
    fun getDeck(id : String) : IDeck? {
        //Should be IRepoException
        throw RepoException("No deck with id $id found")
    }
    /**
     * Search for deck
     *
     * @param keyword Search for deck with keyword
     * @return Can be null if no deck with keyword is found
     */
    fun searchForDeck(keyword : String) : IDeck?
}