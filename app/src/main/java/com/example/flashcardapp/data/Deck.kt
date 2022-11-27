package com.example.flashcardapp.data

import androidx.room.*

@Entity(tableName = "deck_table")
data class Deck(
    @PrimaryKey(autoGenerate = true)
    val deckId: Int,
    @ColumnInfo(name = "deck_name")
    val DeckName: String
)

@Entity(tableName = "card_table")
data class Card(
    @PrimaryKey(autoGenerate = true)
    val cardId: Int,
    @ColumnInfo(name = "card_question")
    val question: String,
    @ColumnInfo(name = "card_answer")
    val answer: String,
    @ColumnInfo(name = "deck_id")
    var associatedDeckId: Int
)

