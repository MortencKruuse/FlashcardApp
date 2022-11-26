package com.example.flashcardapp.repo

import com.example.flashcardapp.data.IDeck
import com.example.flashcardapp.repo.exceptions.RepoException

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
    fun getDeck(id: String): IDeck? {
        //Should be IRepoException TODO(TODO_FIXME)
        throw RepoException("No deck with id $id found")
    }

    /**
     * Search for deck
     *
     * @param keyword Search for deck with keyword
     * @return Can be null if no deck with keyword is found
     */
    fun searchForDeck(keyword: String): Array<IDeck>
}