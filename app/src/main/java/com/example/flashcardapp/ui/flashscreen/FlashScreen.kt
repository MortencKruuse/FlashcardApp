package com.example.flashcardapp.ui.flashscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.flashcardapp.ui.components.Background
import com.example.flashcardapp.ui.components.BackgroundBox
var text = mutableStateOf("Hej")
@Composable
fun FlashScreen( ){
    Background(1f)
    BackgroundBox()
    val myText by text

    Column(
        modifier = Modifier
            .padding(48.dp)

    ) {
        Text("")
        Box(Modifier.clickable { checkAnswer("Hej","Farvel") }){
            Text(text = myText)
        }
    }
}

fun checkAnswer(question: String, answer: String){
    if(text.value == question){
        text.value = answer
    }
    else text.value = question
}