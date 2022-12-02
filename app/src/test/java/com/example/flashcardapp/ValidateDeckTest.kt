package com.example.flashcardapp

import com.example.flashcardapp.domain.helpers.DeckValidator
import com.example.flashcardapp.ui.DTO.DeckDTO
import org.junit.Assert
import org.junit.Test

class ValidateDeckTest {
        private val validate = DeckValidator()
        @Test
        fun validateDeckTestLength() {
            val deckDTO = DeckDTO("testDeck","")
            Assert.assertEquals(
                "Please type a topic before submitting.",
                validate.validateDeck(deckDTO)
            )
            deckDTO.deckTopic = "Do I pass?"
            Assert.assertEquals("", validate.validateDeck(deckDTO))
        }

        @Test
        fun validateDeckTestCharacters() {
            val deckDTO = DeckDTO("testDeck","")
            Assert.assertEquals(
                "Please only use valid characters ([!-~]) in your topic",
                validate.validateDeck(deckDTO).length
            )

            deckDTO.deckTopic = "Do I pass?"
            Assert.assertEquals(validate.validateDeck(deckDTO), "")
        }
}