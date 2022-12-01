package com.example.flashcardapp.data

import androidx.room.*


data class DecksAndCards(
    @Embedded
    val deck: Deck,
    @Relation(
        parentColumn = "id",
        entityColumn = "deck"
    )
    val albums: List<Card>
)