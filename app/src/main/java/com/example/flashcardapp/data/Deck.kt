package com.example.flashcardapp.data

import androidx.annotation.NonNull
import androidx.room.*

@Entity(tableName = "deck_table")
data class Deck(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val deckId: Int,
    @NonNull
    @ColumnInfo(name = "deck_topic")
    val deckTopic: String
)

@Entity(tableName = "card_table")
data class Card(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val cardId: Int,
    @NonNull
    @ColumnInfo(name = "card_topic")
    val cardTopic: String,
    @NonNull
    @ColumnInfo(name = "card_question")
    val question: String,
    @NonNull
    @ColumnInfo(name = "card_answer")
    val answer: String
)

