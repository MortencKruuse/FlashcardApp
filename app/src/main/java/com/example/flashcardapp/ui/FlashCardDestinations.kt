package com.example.flashcardapp.ui


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.flashcardapp.data.Card
import com.example.flashcardapp.data.viewmodels.DeckViewModel
import com.example.flashcardapp.ui.flashscreen.FlashScreen
import com.example.flashcardapp.ui.mainscreen.MainScreen
import com.example.flashcardapp.ui.deckscreen.CardScreen
import com.example.flashcardapp.ui.deckscreen.DeckScreen

interface FlashCardDestination {
    val icon: ImageVector
    val route: String
    val screen: @Composable () -> Unit
}

object MainScreen : FlashCardDestination {
    override val icon = Icons.Default.PieChart
    override val route = "mainscreen"
    override val screen: @Composable () -> Unit = { MainScreen() }
}


object FlashScreen : FlashCardDestination {
    override val icon = Icons.Default.AccountCircle
    override val route = "flashscreen"
    override val screen: @Composable () -> Unit = { FlashScreen() }
}

object DeckScreen : FlashCardDestination {
    override val icon = Icons.Default.AccessAlarm
    override val route = "deckscreen"
    override val screen: @Composable () -> Unit = { DeckScreen() }
}

object CardScreen : FlashCardDestination {
    override val icon = Icons.Default.AccountBalance
    override val route = "cardscreen"
    override val screen: @Composable () -> Unit = { CardScreen() }
}


val flashCardTabRowScreens = listOf(MainScreen, FlashScreen, DeckScreen, CardScreen)
