package com.example.flashcardapp.data

import androidx.room.*

@Entity(tableName = "deck_table")
data class Deck(
    @PrimaryKey(autoGenerate = true)
    val deckId: Int,
    //TODO Ændres til deck_topic og DeckTopic samt de steder med referencer hertil
    @ColumnInfo(name = "deck_name")
    val DeckName: String
)

@Entity(tableName = "card_table")
data class Card(
    @PrimaryKey(autoGenerate = true)
    val cardId: Int,
    //Nedestående linje skal gennemtjekkes for hvordan man bedst henter det topic som et kort er relateret til. Muligvis skal man oprette en relationEntity. Skal undersøges.
    @ColumnInfo(name = "card_topic_is_deck_name")
    val topic: String,
    @ColumnInfo(name = "card_question")
    val question: String,
    @ColumnInfo(name = "card_answer")
    val answer: String,
    //Måske relateret til topic. Noget smart man kan gøre?
    @ColumnInfo(name = "deck_id")
    var associatedDeckId: Int
)

