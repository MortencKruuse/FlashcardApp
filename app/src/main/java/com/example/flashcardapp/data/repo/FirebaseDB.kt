package com.example.flashcardapp.data.repo

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import com.example.flashcardapp.data.Interfaces.ICard
import com.example.flashcardapp.data.Interfaces.IDeck
import com.example.flashcardapp.data.entities.DBCard
import com.example.flashcardapp.data.entities.DBDeck
import com.example.flashcardapp.ui.DTO.CardDTO
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.sentry.Sentry
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow


class FirebaseDB {
    val db = Firebase.firestore

    suspend fun addDeckToFirebase(deck: IDeck /*temp*/) {
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


    suspend fun getDecks() : List<IDeck> {
        var decks = listOf<DBDeck>()
        try {
        db.collection("decks")
            .get()
            .addOnSuccessListener { result ->
               decks = result.mapNotNull { snapShot ->
                    snapShot.toObject(DBDeck::class.java)
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting decks: " + e.message, e)
                Sentry.captureException(e)
            }

        }

        catch (e: Exception) {
            Sentry.captureException(e)
        }
        try {

            for (deck in decks) {
                db.collection("decks")
                    .document(deck.deckId)
                    .collection("cards")
                    .get()
                    .addOnSuccessListener { result ->
                        deck.cards = result.mapNotNull { snapShot ->
                            snapShot.toObject(DBCard::class.java)

                        }
                        Log.e(TAG,"Fuck af" + deck.toString())
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error getting cards: " + e.message, e)
                    }
            }
        }
        catch (e: Exception) {
            Sentry.captureException(e)
        }
        //TODO Remove delay
        delay(1000)
        return decks


    }

    suspend fun getDeck(deckId : String) : IDeck{
        var deck = DBDeck()
        try {
            db.collection("decks")
                .document(deckId)
                .get()
                .addOnSuccessListener { result ->

                    deck = result.toObject(DBDeck::class.java)!!
                    Log.e(TAG,"HEARERASFDASFASFASFASF " + deck.toString())

                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error getting decks: " + e.message, e)
                    Sentry.captureException(e)

                }
        }
        catch (e: Exception) {
            Sentry.captureException(e)
        }
        return deck
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

    fun addCardToFirebase(deckId : String, card: ICard /*temp*/) {
        db.collection("decks")
            .document(deckId)
            .collection("cards")
            .document(card.cardId)
            .set(card)
            .addOnSuccessListener { card ->
                Log.d(TAG, "Card added with ID: $card")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding card:${e.message}", e)
                Sentry.captureException(e)

            }
    }

    suspend fun getCards(deckId : String) = channelFlow{
        var cards = listOf<DBCard>()
        db.collection("decks")
            .document(deckId)
            .collection("cards")
            .get()
            .addOnSuccessListener { result ->
                cards = result.mapNotNull { snapShot ->
                    snapShot.toObject(DBCard::class.java)
                }
                Log.d(TAG,"I AM HERE" +  cards.toString())
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting cards: " + e.message, e)
            }
        delay(1000)
        send(cards)
    }
}