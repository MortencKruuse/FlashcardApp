package com.example.flashcardapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flashcardapp.data.Deck
import com.example.flashcardapp.data.dao.DeckDAO

//TODO Schema can be set to true later
//TODO Don't know which version of db is the current. If it fucks up try incrementing.
@Database(entities = [Deck::class], version = 8, exportSchema = false)
abstract class DeckDatabase : RoomDatabase() {
    abstract fun deckDAO(): DeckDAO

    companion object {
        @Volatile
        private var INSTANCE: DeckDatabase? = null
        fun getDatabase(context: Context): DeckDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DeckDatabase::class.java,
                    "deck_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}