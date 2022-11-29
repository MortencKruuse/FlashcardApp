package com.example.flashcardapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//TODO Schema can be set to true later
//TODO Don't know which version of db is the current. If it fucks up try incrementing.
@Database(entities = [Deck::class, Card::class], version = 9, exportSchema = false)
abstract class FlashcardDatabase : RoomDatabase() {
    abstract fun dao(): DAO

    companion object {
        @Volatile
        private var INSTANCE: FlashcardDatabase? = null
        fun getDatabase(context: Context): FlashcardDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlashcardDatabase::class.java,
                    "flashcard_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}