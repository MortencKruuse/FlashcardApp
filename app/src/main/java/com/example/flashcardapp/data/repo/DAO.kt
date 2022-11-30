package com.example.flashcardapp.data.repo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.Deck


@Dao
interface DAO {

    // DECKS
    //TODO Switch .IGNORE to something more applicable
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDeck(deck: Deck)

    @Query("DELETE FROM deck_table WHERE deckId = :deckId")
    fun deleteDeck(deckId: Int)

    @Query("SELECT * FROM deck_table WHERE deckId == :deckId")
    fun findDeck(deckId: Int): MutableList<Deck>

    @Query("SELECT * FROM deck_table ORDER BY deckId DESC")
    fun readAllDataFromDeck(): LiveData<List<Deck>>


    // CARDS
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCard(card: Card)

    @Query("DELETE FROM card_table WHERE cardId = :cardId")
    fun deleteCard(cardId: Int)

    @Query("SELECT * FROM card_table WHERE cardId = :cardId")
    fun findCard(cardId: Int): MutableList<Card>

    @Query("SELECT * FROM card_table ORDER BY cardId DESC")
    fun readAllDataFromCard(): LiveData<List<Card>>


}