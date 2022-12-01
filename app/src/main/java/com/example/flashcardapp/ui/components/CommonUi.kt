package com.example.flashcardapp.ui.components

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.flashcardapp.R
import com.example.flashcardapp.ui.theme.Purple200
import com.example.flashcardapp.ui.theme.Purple500


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
fun Background(alpha: Float) {
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .blur(6.dp)
                .alpha(alpha),
            contentScale = ContentScale.Crop
        )


    }
}

@Composable
fun BackgroundBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
            .alpha(0.57f)
            .clip(
                CutCornerShape(
                    topStart = 8.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 8.dp
                )
            )
            .background(MaterialTheme.colors.background)
    )
}

@Composable
fun DeckTitleRow(head1: String, head2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Purple500))

    ) {
        Text(
            head2, color = Purple200,
            modifier = Modifier
                .weight(0.5f)
                .padding(3.dp)
        )
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
        Modifier.fillMaxWidth(),
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

