package com.example.flashcardapp.ui


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.flashcardapp.ui.flashscreen.FlashScreen
import com.example.flashcardapp.ui.mainscreen.MainScreen

interface FlashCardDestination {
    val icon: ImageVector
    val route: String
    val screen: @Composable () -> Unit
}

object MainScreen : FlashCardDestination {
    override val icon = Icons.Default.PieChart
    override val route = "mainscreen"
    override val screen: @Composable () -> Unit = { MainScreen()}
}


object FlashScreen : FlashCardDestination {
    override val icon = Icons.Default.AccountCircle
    override val route = "flashscreen"
    override val screen: @Composable () -> Unit = { FlashScreen()}
}

val flashCardTabRowScreens = listOf(MainScreen, FlashScreen)
