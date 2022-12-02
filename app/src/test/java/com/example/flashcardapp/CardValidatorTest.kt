package com.example.flashcardapp

import com.example.flashcardapp.domain.helpers.CardValidator
import com.example.flashcardapp.ui.DTO.CardDTO
import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ValidateCardTest {
    private val validate = CardValidator()
    @Test
    fun validateCardTestLength() {
        val cardDTO = CardDTO("testCard","","")
        assertEquals("Please type a question before submitting.",validate.validateCard(cardDTO))
        cardDTO.question = "Do I pass?"
        assertEquals("Please type an answer before submitting.",validate.validateCard(cardDTO))
        cardDTO.answer = "Do I pass?"
        assertEquals("",validate.validateCard(cardDTO))
    }

    @Test
    fun validateCardTestCharacters() {
        val cardDTO = CardDTO("testCard","ø","½")
        assertEquals("Please only use valid characters ([!-~]) in your question",validate.validateCard(cardDTO).length)
        cardDTO.question = "Do I pass?"
        assertEquals("Please only use valid characters ([!-~]) in your question",validate.validateCard(cardDTO).length)
        cardDTO.answer = "Do I pass?"
        assertEquals(validate.validateCard(cardDTO), "")
    }

}