package com.example.flashcardapp.data.repo

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.Deck
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.reflect.KParameter


class FirebaseDB {
    val db = Firebase.firestore

    fun addDeckToFirebase(deck: Deck /*temp*/) {
        db.collection("decks")
            .add(deck)
            .addOnSuccessListener { deck ->
                Log.d(TAG, "Deck added with ID: ${deck.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding deck:${e.message}", e)
            }
    }

    fun getDecks() {
        db.collection("decks")
            .get()
            .addOnSuccessListener { result ->
                for (deck in result) {
                    Log.d(TAG, "${deck.id} => ${deck.data}")
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting decks: " + e.message, e)
            }
    }

    fun addCardToFirebase(card: Card /*temp*/) {
        db.collection("cards")
            .add(card)
            .addOnSuccessListener { card ->
                Log.d(TAG, "Card added with ID: ${card.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding card:${e.message}", e)
            }
    }
/*
    fun getCards() : MutableList<Card> {
        var returnVal : MutableList<Card>
        db.collection("cards")
            .get()
            .addOnSuccessListener { result ->
                for (card in result) {
                    Log.d(TAG, "${card.id} => ${card.data}")
                }

            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting cards: " + e.message, e)
            }
    }*/
}