package com.example.flashcardapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deck_table")
data class DeckEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val DeckName: String,
    // List[] of type "card"? idk
    //...
    // DeckEntity placeholder

    )