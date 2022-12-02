package com.example.flashcardapp.ui.flashscreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashcardapp.R
import com.example.flashcardapp.ui.components.Background
import com.example.flashcardapp.ui.components.BackgroundBox
import com.example.flashcardapp.ui.theme.ExtraSquares
import com.example.flashcardapp.ui.theme.Purple200
import com.example.flashcardapp.ui.theme.TextColour

var text = mutableStateOf("God Morgen")
val myText by text
var showQuestion = mutableStateOf("Question")
val showQuestionString by showQuestion


@Composable
fun FlashScreen(deckId: String?) {

    Background()
    BackgroundBox()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.applogohdpi), contentDescription = "App logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(140.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {

            CreateBox(question = "God Morgen", answer = "God aften", value = myText)
            Spacer(modifier = Modifier.height(8.dp))
            ChangeCardButtons()
        }

    }
}

fun checkAnswer(question: String, answer: String) {
    if (text.value == question) {
        text.value = answer
        showQuestion.value = "Answer"
    } else {
        text.value = question
        showQuestion.value = "Question"
    }
}

fun incrementDeck() {
    text.value = "Farvel"
}


@Composable
fun CreateBox(question: String, answer: String, value: String) {
    var rotated by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateColor by animateColorAsState(
        targetValue = if (rotated) ExtraSquares else ExtraSquares,
        animationSpec = tween(500)
    )

    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable { checkAnswer(question, answer)
        rotated = !rotated}
        .fillMaxSize(0.7f)
        .clip(RoundedCornerShape(10))
        .background(color = ExtraSquares)
        .graphicsLayer { rotationY = rotation
        cameraDistance = 8 * density},contentAlignment = Alignment.TopCenter){
        Text(text = showQuestionString, modifier = Modifier.graphicsLayer  { alpha = if (rotated) animateBack else animateFront
            rotationY = rotation }, textAlign = TextAlign.Center, color = TextColour)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {


            Text(
                text = value, Modifier.padding(16.dp).graphicsLayer { alpha = if (rotated) animateBack else animateFront
                                                                    rotationY = rotation},
                textAlign = TextAlign.Center,
                style = typography.h4, fontSize = 25.sp, color = TextColour
            )
        }
    }




}

@Composable
fun ChangeCardButtons() {
    Row(
        modifier = Modifier
    
    ) {
        Button(modifier = Modifier
            .weight(1f)
            .wrapContentWidth(Alignment.Start),
            shape = RoundedCornerShape(50),
            onClick = { /*TODO*/ }) {
            Text(text = "Prev card")
        }


        Button(modifier = Modifier
            .weight(1f)
            .wrapContentWidth(Alignment.End)
            ,
            shape = RoundedCornerShape(50),
            onClick = { /*TODO*/ }) {

            Text(text = "Next card")


        }


    }
}