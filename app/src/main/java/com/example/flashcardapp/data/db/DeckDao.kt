package com.example.flashcardapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flashcardapp.data.*


@Dao
interface DeckDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDeck(deck: DeckEntity)

    @Query("SELECT * FROM decks ORDER BY id ASC")
    suspend fun getAll(): LiveData<List<DeckEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDeck(updatedDeck: DeckEntity, deckId: Int);

    @Delete
    suspend fun deleteDeck(deckId: Int);

    @Query("SELECT * FROM decks WHERE id LIKE :id LIMIT 1")
    suspend fun findDeckbyID(id: Int)


}