package com.example.flashcardapp.data.db

import android.content.Context
import androidx.room.*
import androidx.room.Database

import com.example.flashcardapp.data.Deck


@Database(
    entities = [Deck::class/*, Feature::class*/],
    version = 2,
    exportSchema = false
)
abstract class appDB : RoomDatabase(){

    abstract fun deckDao(): DeckDao

    abstract fun otherDao(): OtherDao

    companion object {
        @Volatile
        private var INSTANCE: appDB? = null

        fun getDatabase(context: Context): appDB {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    appDB::class.java,
                    "app_DB"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}