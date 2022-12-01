package com.example.flashcardapp.data

import androidx.room.*

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
    val cardId: String,
    @ColumnInfo(name = "card_question")
    val question: String,
    @ColumnInfo(name = "card_answer")
    val answer: String,
    @ColumnInfo(index = true)
    val deckId: String
)

data class DecksAndCards(
    @Embedded
    val deck: Deck,
    @Relation(
        parentColumn = "id",
        entityColumn = "deck"
    )
    val cards: List<Card>
)