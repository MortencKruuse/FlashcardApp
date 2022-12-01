package com.example.flashcardapp.data.repo

import android.content.ContentValues.TAG
import android.util.Log
import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.Interfaces.ICard
import com.example.flashcardapp.data.Interfaces.IDeck
import com.example.flashcardapp.data.Response
import com.example.flashcardapp.data.entities.DBDeck
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import io.sentry.Sentry


class FirebaseDB {
    val db = Firebase.firestore

    fun addDeckToFirebase(deck: Deck /*temp*/) {
        db.collection("decks")
            .document(deck.deckId)
            .set(deck)
            .addOnSuccessListener { deck ->
                Log.d(TAG, "Deck added with ID: $deck")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding deck:${e.message}", e)
                Sentry.captureException(e)
            }

        getDecks()

    }


    fun getDecks() : Response {
        val response = Response()
        try {
        db.collection("decks")
            .get()
            .addOnSuccessListener { result ->
                response.decks = result.mapNotNull { snapShot->
                    snapShot.toObject(DBDeck::class.java)
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting decks: " + e.message, e)
                Sentry.captureException(e)

            }
        }
        catch (e: Exception) {
            response.exception = e
            Sentry.captureException(e)
        }

        return response
    }


    fun deleteDeck(deckId : String) {
        db.collection("decks")
            .document(deckId)
            .delete()
            .addOnSuccessListener { result ->
                Log.d(TAG, "Deleted deck: $result")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error deleting deck: " + e.message, e)
                Sentry.captureException(e)
            }
    }



    fun addCardToFirebase(deckId : String, cardDAO: Card /*temp*/) {
        db.collection("decks")
            .document(deckId)
            .collection("cards")
            .document(cardDAO.cardId)
            .set(cardDAO)
            .addOnSuccessListener { card ->
                Log.d(TAG, "Card added with ID: ${card}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding card:${e.message}", e)
                Sentry.captureException(e)

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