package com.example.flashcardapp.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
internal abstract class BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: T)
    @Update
    abstract fun update(entity: T)
    @Delete
    abstract fun delete(entity: T)
}

@Dao
interface DAO {

    // DECKS
    //TODO Switch .IGNORE to something more applicable
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDeck(deck: Deck)

    @Query("DELETE FROM deck_table WHERE deckId = :deckId")
    fun deleteDeck(deckId: Int)

    @Query("SELECT * FROM deck_table WHERE deckId == :id")
    fun findDeck(id: Int): MutableList<Deck>

    @Query("SELECT * FROM deck_table ORDER BY deckId DESC")
    fun readAllDataFromDeck(): LiveData<List<Deck>>


}