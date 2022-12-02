package com.example.flashcardapp.data

import androidx.room.Embedded
import androidx.room.Relation


data class DecksAndCards(
    @Embedded
    val deck: Deck,
    @Relation(
        parentColumn = "deckId",
        entityColumn = "cardId"
    )
    val cards: List<Card>
)
