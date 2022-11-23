package com.example.flashcardapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flashcardapp.data.Deck

@Dao
interface DeckDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDeck(deck: Deck)

    @Query("SELECT * FROM deck_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Deck>>
}