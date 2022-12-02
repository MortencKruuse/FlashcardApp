package com.example.flashcardapp.ui.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashcardapp.R
import com.example.flashcardapp.ui.components.Background
import com.example.flashcardapp.ui.components.BackgroundBox
import com.example.flashcardapp.ui.theme.ExtraSquares


@Composable
fun MainScreen(
    onNavigateToDeck: () -> Unit,
    onNavigateToTopic: () -> Unit
) {
    Background()
    BackgroundBox()
    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "Overview Screen" }

    ) {
        MainMenu(onNavigateToDeck, onNavigateToTopic)
    }
}


@Composable
fun MainMenu(onNavigateToDeck: () -> Unit, onNavigateToTopic: () -> Unit) {

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.applogohdpi), contentDescription = "App logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp)))
        Text(text = stringResource(id = R.string.frontpage_texttitle), fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Text(text = stringResource(id = R.string.frontpage_textsubtitle), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(48.dp))
        Button(onClick = onNavigateToDeck, modifier = Modifier.fillMaxWidth(0.6f)) {
            Text(text = stringResource(id = R.string.frontpage_deckscreen))
        }
        Button(onClick = onNavigateToTopic,modifier = Modifier.fillMaxWidth(0.6f)) {
            Text(text = stringResource(id = R.string.frontpage_topicscreen))

        }
    }


}


private val RallyDefaultPadding = 12.dp

