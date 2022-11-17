package com.example.flashcardapp.ui.mainscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.flashcardapp.ui.components.FlashCardAlertDialog
import com.example.flashcardapp.ui.components.FlashCardDivider
import java.util.*



@Composable
fun MainScreen(
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "Overview Screen" }
    ) {
        AlertCard()
    }
}


@Composable
private fun AlertCard() {
    var showDialog by remember { mutableStateOf(false) }
    val alertMessage = "Heads up, you've used up 90% of your Shopping budget for this month."

    if (showDialog) {
        FlashCardAlertDialog(
            onDismiss = {
                showDialog = false
            },
            bodyText = alertMessage,
            buttonText = "Dismiss".uppercase(Locale.getDefault())
        )
    }
    Card {
        Column {
            AlertHeader {
                showDialog = true
            }
            FlashCardDivider(
                modifier = Modifier.padding(start = RallyDefaultPadding, end = RallyDefaultPadding)
            )
            AlertItem(alertMessage)
        }
    }
}

/**
 * Alert header
 *
 * @param onClickSeeAll
 * @receiver
 */
@Composable
private fun AlertHeader(onClickSeeAll: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(RallyDefaultPadding)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Alerts",
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        TextButton(
            onClick = onClickSeeAll,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(
                text = "SEE ALL",
                style = MaterialTheme.typography.button,
            )
        }
    }
}

@Composable
private fun AlertItem(message: String) {
    Row(
        modifier = Modifier
            .padding(RallyDefaultPadding)
            // Regard the whole row as one semantics node. This way each row will receive focus as
            // a whole and the focus bounds will be around the whole row content. The semantics
            // properties of the descendants will be merged. If we'd use clearAndSetSemantics instead,
            // we'd have to define the semantics properties explicitly.
            .semantics(mergeDescendants = true) {},
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            style = MaterialTheme.typography.body2,
            modifier = Modifier.weight(1f),
            text = message
        )
        IconButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.Top)
                .clearAndSetSemantics {}
        ) {
            Icon(Icons.Filled.Sort, contentDescription = null)
        }
    }
}





private val RallyDefaultPadding = 12.dp

