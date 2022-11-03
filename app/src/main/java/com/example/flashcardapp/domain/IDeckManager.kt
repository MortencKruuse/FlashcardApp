package com.example.flashcardapp.domain

import com.example.flashcardapp.data.IDeck

interface IDeckManager {
    fun getDeck() : IDeck

}