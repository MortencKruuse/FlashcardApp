package com.example.flashcardapp.data

/**
 * I deck
 *
 * @constructor Create empty I deck
 */
interface IDeck {
    var cardList: MutableList<ICard>
    var id: String
    var topic: String
    /**
     * Card list
     */


}