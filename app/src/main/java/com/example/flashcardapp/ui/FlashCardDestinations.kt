package com.example.flashcardapp.ui


import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.flashcardapp.ui.mainscreen.MainScreen
import com.example.flashcardapp.ui.flashscreen.FlashScreen

interface FlashCardDestination {
    val icon: ImageVector
    val route: String
    val screen: @Composable () -> Unit
}

object MainScreen : FlashCardDestination {
    override val icon: ImageVector
        get() = TODO("Not yet implemented")
    override val route = "mainscreen"
    override val screen: @Composable () -> Unit = { MainScreen()}
}


object FlashScreen : FlashCardDestination {
    override val icon: ImageVector
        get() = TODO("Not yet implemented")
    override val route = "flashscreen"
    override val screen: @Composable () -> Unit = { FlashScreen()}
}