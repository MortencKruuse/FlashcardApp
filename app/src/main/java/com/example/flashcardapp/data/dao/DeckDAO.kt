package com.example.flashcardapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.Deck

@Dao
interface DeckDAO {

    // DECKS
    //TODO Switch .IGNORE to something more applicable
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addDeck(deck: Deck)

    @Delete
    fun deleteDeck(deck: Deck)

    @Query("SELECT * FROM deck_table WHERE deckId == :id")
    fun findDeck(id: Int): MutableList<Deck>

    @Query("SELECT * FROM deck_table ORDER BY deckId DESC")
    fun readAllDataFromDeck(): LiveData<List<Deck>>

    @Insert
    fun insertAll(vararg decks: Deck)
}