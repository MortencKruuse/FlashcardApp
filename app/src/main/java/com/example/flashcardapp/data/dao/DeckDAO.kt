package com.example.flashcardapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.DeckWithCards

@Dao
interface DeckDAO {

    // DECKS
    //TODO Switch .IGNORE to something more applicable
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addDeck(deck: Deck)

    @Query("SELECT * FROM deck_table ORDER BY deckId DESC")
    fun readAllDataFromDeck(): LiveData<List<Deck>>

    // CARDS
    //TODO Switch .IGNORE to something more applicable
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCard(deck: Deck)

    @Query("SELECT * FROM card_table ORDER BY deck_id DESC")
    fun readAllDataFromCard(): LiveData<List<Card>>

    // RELATION WITH DECK AND CARD
    @Transaction
    @Query("SELECT * FROM deck_table")
    fun getDecksWithCards(): List<DeckWithCards>
}