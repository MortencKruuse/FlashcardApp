package com.example.flashcardapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entites = [Deck::class], version = 1, exportSchema = false)
abstract class DeckDatabase: RoomDatabase(){

    abstract fun DeckDAO(): DeckDAO

    companion object{
        @Volatile
        private var INSTANCE: DeckDatabase? = null

        fun getDatabase(context: Context): DeckDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance
        }
        synchronized(this){
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