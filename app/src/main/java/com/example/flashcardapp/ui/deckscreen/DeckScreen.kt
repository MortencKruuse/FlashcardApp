package com.example.flashcardapp.ui.deckscreen



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun DeckScreen() {
    var topic by remember() {
        mutableStateOf("")
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldWithIconsDeck("Topic", "Enter a topic name for your deck")
        {
            topic = it
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            /*TODO*/
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Submit")
        }

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            //val list = if (searching) searchResults else allProducts

            item {
                DeckTitleRow(head1 = "ID", head2 = "Deck Topic")
            }

            items(1) { deck ->
                DeckRow(id = 1, name = "The hard truth of egg white")
            }
        }
    }
}

@Composable
fun DeckTitleRow(head1: String, head2: String) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(head1, color = Color.White,
            modifier = Modifier
                .weight(0.1f))
        Text(head2, color = Color.White,
            modifier = Modifier
                .weight(0.5f))
    }
}

@Composable
fun DeckRow(id: Int, name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(id.toString(), modifier = Modifier
            .weight(0.1f))
        Text(name, modifier = Modifier.weight(0.5f))
    }
}


@Composable
fun TextFieldWithIconsDeck(label: String,placeholder: String, thingie :(String) -> Unit) {
    return OutlinedTextField(
        value = "" ,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        onValueChange = thingie,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
    )
}

