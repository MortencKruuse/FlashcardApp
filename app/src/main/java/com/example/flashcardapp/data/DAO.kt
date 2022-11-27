package com.example.flashcardapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


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


    // CARDS
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCard(card: Card)

    @Query("DELETE FROM card_table WHERE cardId = :cardId")
    fun deleteCard(cardId: Int)

    @Query("SELECT * FROM card_table ORDER BY cardId DESC")
    fun readAllDataFromCard(): LiveData<List<Card>>


}