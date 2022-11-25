package com.example.flashcardapp.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.example.flashcardapp.data.ICard

//this is currently "ish" what i am working out from
// - Philip
// i'm thinking whatever structures are made for decks or other features can be mapped into @Entities like these, for the db

@Entity(tableName = "decks")
data class deckEntity(
    @PrimaryKey(autoGenerate = true)val id: Int,
    @ColumnInfo(name = "deck_name") val DeckName: String,
    @ColumnInfo(name ="card_list") var cardList: MutableList<ICard>
    //...
    // DeckEntity placeholder

)

@Entity(tableName = "other_features")
data class Feature(
    @PrimaryKey val id: Int,    //set ID to corresponding feature?
    @ColumnInfo(name = "feature_enable") val status: Boolean, //check to see if it should be active
    //...
    // FeatureEntity placeholder
)

/*
@Entity
data class Deck(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "deckName")
    val deckName: String,

    @ColumnInfo(name = "card_list")
    var cardList: MutableList<ICard>
)

fun Deck.init(name: String): Deck =
    Deck(0, name, ArrayList())


fun Deck.get(id: Int): Deck =
    TODO()

//fun Deck.addCard(id: Int, card: ICard): {
//    var thisDeck = Deck.get(id)

//  TODO()

//}
*/