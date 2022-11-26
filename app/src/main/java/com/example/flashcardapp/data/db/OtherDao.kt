package com.example.flashcardapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface OtherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add()

    @Query("SELECT * FROM decks ORDER BY id ASC")
    suspend fun getAll(): LiveData<List<String>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update();

    @Delete
    suspend fun delete();

    @Query("SELECT * FROM decks WHERE id LIKE :id LIMIT 1")
    suspend fun findbyID(id: Int)

}