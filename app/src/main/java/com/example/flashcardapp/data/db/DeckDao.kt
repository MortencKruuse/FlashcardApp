package com.example.flashcardapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flashcardapp.data.Deck


@Dao
interface DeckDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDeck(deck: Deck)

    @Query("SELECT * FROM decks ORDER BY id ASC")
    suspend fun getAll(): LiveData<List<Deck>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDeck(updatedDeck: Deck, deckId: Int);

    @Delete
    suspend fun deleteDeck(deckId: Int);

    @Query("SELECT * FROM decks WHERE id LIKE :id LIMIT 1")
    suspend fun findDeckbyID(id: Int)


}