package com.example.flashcardapp.data

import androidx.room.*
import com.example.flashcardapp.data.Interfaces.ICard

@Entity(tableName = "deck_table")
data class Deck(
    @PrimaryKey()
    val deckId: String,
    @ColumnInfo(name = "deck_topic")
    val deckTopic: String
)
@Entity(
    foreignKeys = [ForeignKey(
        entity = Deck::class,
        parentColumns = arrayOf("deckId"),
        childColumns = arrayOf("cardId"),
        onDelete = ForeignKey.CASCADE
    )])

data class Card (
    @PrimaryKey()
    val cardId: String,
    @ColumnInfo(name = "card_question")
    val question: String,
    @ColumnInfo(name = "card_answer")
    val answer: String,
    @ColumnInfo(index = true)
    val deckId: String
)



