package com.example.flashcardapp

import com.example.flashcardapp.repo.DeckHandler
import com.example.flashcardapp.repo.exceptions.RepoException
import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepoUnitTest {
    private val dh = DeckHandlerTest()
    /**
     * Used to avoid remote database access
     */
    private class DeckHandlerTest : DeckHandler()

    @Test
    fun deckHandlerGetDeckTest() {
        assertEquals(dh.getDeck("testDeck"), "testDeck")
        assertNotEquals(dh.getDeck("test"), "testDeck")
    }
    @Test
    fun deckHandlerSearchForDeckTest() {
        var i = 0
        for  (str in dh.searchForDeck("testDeck")){
            if (i == 0){
                assertEquals(str, "testDeck")
            } else {
                assertEquals(str, "testDeck$i")
            }
            i++
        }
    }
    @Test
    fun exceptionTest() {
        val arr = arrayOf("\\","æ","ø","å","-","¨","¨","´","'","*","`","`","^","~","½",".",":",";","_")
        for (str in arr){
            assertThrows(RepoException::class.java) {
                dh.getDeck("str")
            }
        }
    }




}