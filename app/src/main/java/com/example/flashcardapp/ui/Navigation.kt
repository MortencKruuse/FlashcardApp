package com.example.flashcardapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flashcardapp.ui.deckscreen.CardScreen
import com.example.flashcardapp.ui.deckscreen.DeckScreen
import com.example.flashcardapp.ui.deckscreen.EditCardScreen
import com.example.flashcardapp.ui.flashscreen.FlashScreen
import com.example.flashcardapp.ui.mainscreen.MainScreen


@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "mainScreen"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("mainScreen") {
            MainScreen(
                onNavigateToDeck = { navController.navigate("deckScreen") },
                onNavigateToFlash = { navController.navigate("flashScreen") }
            )
        }
        composable("deckScreen")
        {
            DeckScreen(
                navController
                //     onNavigateToCard = { navController.navigate("cardScreen/$id")}
            )
        }

        composable(
            "cardScreen/{deckId}/{deckTopic}",
            arguments = listOf(navArgument("deckId") { type = NavType.IntType },
                navArgument("deckTopic") { type = NavType.StringType })
        ) {
            CardScreen(
                it.arguments?.getInt("deckId"),
                navController,
                it.arguments?.getString("deckTopic")
            )
        }
        composable("flashScreen") { FlashScreen() }

        composable(
            "editCardScreen/{cardID}",
            arguments = listOf(navArgument("cardID") { type = NavType.IntType })
        ) {
            EditCardScreen(it.arguments?.getInt("cardID"))
        }
    }
}

