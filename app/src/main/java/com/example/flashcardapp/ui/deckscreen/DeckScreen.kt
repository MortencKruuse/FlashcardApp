package com.example.flashcardapp.ui.deckscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun DeckScreen() {
    var cardID by remember(){
        mutableStateOf("")
    }

    var question by remember(){
        mutableStateOf("")
    }

    var answer by remember(){
        mutableStateOf("")
    }


    Column(
        Modifier.fillMaxSize().padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldWithIcons("cardId", "Enter cardId") { cardID = it }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldWithIcons("question", "question your e-mail"){ question = it }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldWithIcons("answer", "Enter your answer"){ answer = it }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
                         /*TODO*/
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Submit")
        }
    }

}


@Composable
fun TextFieldWithIcons(label: String,placeholder: String, thingie :(String) -> Unit) {
    return OutlinedTextField(
        value = "" ,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        onValueChange = thingie,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
    )
}

