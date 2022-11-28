package com.example.flashcardapp.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deck_table")
data class Deck(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val deckId: Int,
    @NonNull
    @ColumnInfo(name = "deck_topic")
    val deckTopic: String
)

