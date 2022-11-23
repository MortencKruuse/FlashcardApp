package com.example.flashcardapp.data

data class Deck(override var id: String, override var topic: String) : IDeck {
    override var cardList: MutableList<ICard>
        get() {
            return cardList
        }
        set(cards) {
            cardList = cards
        }

}