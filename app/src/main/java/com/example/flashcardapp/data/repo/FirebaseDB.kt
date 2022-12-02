package com.example.flashcardapp.data.repo

import android.content.ContentValues.TAG
import android.util.Log
import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.Response
import com.example.flashcardapp.data.SingleResponse
import com.example.flashcardapp.data.entities.CardResponse
import com.example.flashcardapp.data.entities.DBCard
import com.example.flashcardapp.data.entities.DBDeck
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.sentry.Sentry


class FirebaseDB {
    val db = Firebase.firestore

    fun addDeckToFirebase(deck: Deck /*temp*/) {
        getDecks()
        db.collection("decks")
            .document(deck.deckId)
            .set(deck)
            .addOnSuccessListener { result ->
                Log.d(TAG, "Deck added with ID: $result")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding deck:${e.message}", e)
                Sentry.captureException(e)
            }
    }


    fun getDecks() : Response {
        val response = Response()
        try {
        db.collection("decks")
            .get()
            .addOnSuccessListener { result ->
                response.decks = result.mapNotNull { snapShot ->
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
    fun getDeck(deckId : String) : SingleResponse {
        val response = SingleResponse()
        try {
            db.collection("decks")
                .document(deckId)
                .get()
                .addOnSuccessListener { result ->
                    response.deck = result.toObject(DBDeck::class.java)
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

    fun getCards(deckId : String) : CardResponse {
        val response = CardResponse()
        db.collection("decks")
            .document(deckId)
            .collection("cards")
            .get()
            .addOnSuccessListener { result ->
                response.cards = result.mapNotNull { snapShot ->
                    snapShot.toObject(DBCard::class.java)

                }
                Log.d(TAG,"I AM HERE" +  response.cards.toString())

            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting cards: " + e.message, e)
            }
        return response
    }
}