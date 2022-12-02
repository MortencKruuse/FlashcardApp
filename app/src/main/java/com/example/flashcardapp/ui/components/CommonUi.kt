package com.example.flashcardapp.ui.components

import android.webkit.WebSettings.TextSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashcardapp.R
import com.example.flashcardapp.ui.theme.MainBackground
import com.example.flashcardapp.ui.theme.Purple200
import com.example.flashcardapp.ui.theme.Purple500
import com.example.flashcardapp.ui.theme.TextColour


@Composable
fun Card() {

}

@Composable
fun CardText() {

}

@Composable
fun DeckText() {

}

@Composable
fun FlashCardDivider(modifier: Modifier = Modifier) {
    Divider(color = MaterialTheme.colors.background, thickness = 1.dp, modifier = modifier)
}

@Composable
fun Background() {
    Box(Modifier.fillMaxSize().background(MainBackground)) {


    }
}

@Composable
fun BackgroundBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
            .alpha(0.57f)
            .background(MaterialTheme.colors.background)
    )
}

@Composable
fun DeckTitleRow(head1: String, head2: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()){
        Column {
            Text(
                head2, color = TextColour,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                fontSize = 30.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold
            )
            Text(
                head1, color = TextColour,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                fontSize = 16.sp,
                textAlign = TextAlign.Left
            )
        }

    }


}

@Composable
fun DemoField(
    value: String,
    label: String,
    placeholder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(

        value = value,
        onValueChange = onValueChange,
        Modifier.fillMaxWidth().padding(8.dp),
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
    )
}

