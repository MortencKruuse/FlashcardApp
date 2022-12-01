package com.example.flashcardapp.ui.flashscreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashcardapp.R
import com.example.flashcardapp.ui.components.Background
import com.example.flashcardapp.ui.components.BackgroundBox

var text = mutableStateOf("God Morgen")
val myText by text
var showQuestion = mutableStateOf("Question")
val showQuestionString by showQuestion
val purple200 = Color(0xFFBB86FC)


@Composable
fun FlashScreen() {

    Background(1f)
    BackgroundBox()

    
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(48.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.applogohdpi), contentDescription = "App logo",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(16.dp)))
        
        Text(text = "You're training x topic")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(text = showQuestionString,textAlign = TextAlign.Center )
            CreateBox(question = "God Morgen", answer = "God aften", value = myText, purple200)
            ChangeCardButtons(purple200)
        }

    }
    }

fun checkAnswer(question: String, answer: String) {
    if (text.value == question) {
        text.value = answer
        showQuestion.value = "Answer"
    } else{
        text.value = question
        showQuestion.value = "Question"
    }
}

fun incrementDeck(){
    text.value = "Farvel"
}


@Composable
fun CreateBox(question: String, answer: String, value: String,color: Color) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .clickable { checkAnswer(question, answer) }
                .fillMaxSize(0.7f)
                .clip(RoundedCornerShape(10))
                .border(width = 4.dp, color = color, shape = RoundedCornerShape(10))
                .background(color = Color.White),
                contentAlignment = Alignment.Center
                ){
                Text(text = value, Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = typography.h4 ,fontSize = 25.sp, color = Color.Black)
            }



}

@Composable
fun ChangeCardButtons(color: Color) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .padding(16.dp)
    ) {
        Button(modifier = Modifier
            .weight(1f)
            .padding(start = 4.dp)
            .wrapContentWidth(Alignment.Start),
            border = BorderStroke(1.dp, color),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = color),
            onClick = { /*TODO*/ }) {
            Text(text = "Prev card")
        }


            Divider(
                color = Color.Black,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Button(modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),
                border = BorderStroke(1.dp, color),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = color),
                onClick = { /*TODO*/ }) {

                Text(text = "Next card")


            }


    }
}