package com.example.flashcardapp.data.repo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.DecksAndCards


@Dao
interface DAO {

    // DECKS
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDeck(deck: Deck)

    @Query("DELETE FROM deck_table WHERE deckId = :deckId")
    fun deleteDeck(deckId: String)

    @Query("SELECT * FROM deck_table WHERE deckId == :deckId")
    fun findDeck(deckId: String): MutableList<Deck>

    @Query("SELECT * FROM deck_table ORDER BY deckId DESC")
    fun readAllDecks(): LiveData<List<Deck>>

    //___________________CARDS___________________

    //@Transaction should be transaction
    @Query("SELECT * FROM deck_table")
    suspend fun getAll(): DecksAndCards

    //@Transaction should be transaction
    @Query("SELECT * FROM deck_table WHERE deckId = :id")
    suspend fun getAllCardsByDeckId(id: String): DecksAndCards

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCard(card: Card)

    //Unsure if works (probably does not)
    @Query("DELETE FROM Card WHERE cardId = :cardId")
    fun deleteCard(cardId: String)

    @Query("SELECT * FROM Card WHERE cardId = :cardId")
    fun findCard(cardId: String): MutableList<Card>

}