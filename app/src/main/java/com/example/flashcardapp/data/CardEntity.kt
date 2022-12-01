package com.example.flashcardapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "deck_table")
data class Deck(
    @PrimaryKey(autoGenerate = true)
    val deckId: String,
    @ColumnInfo(name = "deck_topic")
    val deckTopic: String
)
@Entity(
    foreignKeys = [ForeignKey(
        entity = Deck::class,
        parentColumns = arrayOf("deckId"),
        childColumns = arrayOf("deck"),
        onDelete = ForeignKey.CASCADE
    )])

data class Card(
    @PrimaryKey(autoGenerate = true)
    val cardId: Int,
    @ColumnInfo(name = "card_question")
    val question: String,
    @ColumnInfo(name = "card_answer")
    val answer: String,
    @ColumnInfo(index = true)
    val deck: Deck
)