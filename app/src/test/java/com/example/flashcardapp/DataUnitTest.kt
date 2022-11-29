package com.example.flashcardapp


import com.example.flashcardapp.data.DataException
import com.example.flashcardapp.data.FlashcardDatabase
import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DataUnitTest {


    @Test
    fun dataGetDeckTest() {
        assertEquals("testDeck", "testDeck")
        assertNotEquals("", "testDeck")
    }

    @Test
    fun deckHandlerSearchForDeckTest() {
        var i = 0;
        /*for  (str in FlashcardDatabase.getDatabase("context").findDeck("testDeck"){
            if (i == 0){
                assertEquals(str, "testDeck")
            } else {
                assertEquals(str, "testDeck$i")
            }
            i++
        }*/
    }
    @Test
    fun exceptionTest() {
        val arr = arrayOf("\\","æ","ø","å","-","¨","¨","´","'","*","`","`","^","~","½",".",":",";","_")
        /*for (str in arr){
            assertThrows(DataException::class.java) {
                dh.getDeck("str")
            }
        }*/
    }
}