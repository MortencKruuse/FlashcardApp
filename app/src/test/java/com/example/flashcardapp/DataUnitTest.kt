package com.example.flashcardapp

import com.example.flashcardapp.data.repo.FirebaseDB
import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepoUnitTest {
    private val dh = FirebaseDB()
    /**
     * Used to avoid remote database access
     */
    //private class DeckHandlerTest : FirebaseDB()

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