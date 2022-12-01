package com.example.flashcardapp.ui.flashscreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashcardapp.R
import com.example.flashcardapp.ui.components.Background
import com.example.flashcardapp.ui.components.BackgroundBox
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

var text = mutableStateOf("God Morgen")
val myText by text


@Composable
fun FlashScreen() {


    Background(1f)
    BackgroundBox()




    Column(
        modifier = Modifier
            .padding(48.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        CreateBox(question = "God Morgen", answer = "God aften", value = myText)
        Text("")

    }
}

fun checkAnswer(question: String, answer: String) {
    if (text.value == question) {
        text.value = answer
    } else text.value = question
}

fun incrementDeck(){

}


@Composable
fun CreateBox(question: String, answer: String, value: String) {

    val leftSwipe = SwipeAction(
        icon = painterResource(id = R.drawable.background),
        background = Color.Green,
        isUndo = true,
        onSwipe = {
            incrementDeck()
        }
    )

    val rightSwipe = SwipeAction(
        icon = painterResource(id = R.drawable.background),
        background = Color.Yellow,
        isUndo = true,
        onSwipe = {
            incrementDeck()

        }
    )

        SwipeableActionsBox(startActions = listOf(leftSwipe), endActions = listOf(rightSwipe)) {
            Button(modifier = Modifier.fillMaxWidth(),
                onClick = { checkAnswer(question,answer) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))
            ) {
                Text(text = value, fontSize = 25.sp, color = Color.White)



        }
    }

}

