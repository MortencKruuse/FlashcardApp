package com.example.flashcardapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/*
@Entity(tableName = "users")
data class User (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)
 */

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
data class Deck(override var id: String, override var topic: String) : IDeck {
    override var cardList: MutableList<ICard>
        get() {
            return cardList
        }
        set(cards) {
            cardList = cards
        }

}*/