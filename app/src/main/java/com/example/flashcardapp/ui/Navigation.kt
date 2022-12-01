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
import com.example.flashcardapp.ui.selecttopicscreen.SelectTopicScreen
import io.sentry.compose.withSentryObservableEffect



@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController().withSentryObservableEffect(),
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
                onNavigateToTopic = { navController.navigate("selectTopicScreen") }
            )
        }
        composable("deckScreen")
        {
            DeckScreen(
                navController
            )
        }

        composable("flashScreen/{deckId}",arguments = listOf(navArgument("deckId") { type = NavType.StringType })) { FlashScreen(it.arguments?.getString("deckId")) }

        composable("selectTopicScreen") { SelectTopicScreen(navController) }

        composable(
            "cardScreen/{deckId}/{deckTopic}",
            arguments = listOf(navArgument("deckId") { type = NavType.StringType },
                navArgument("deckTopic") { type = NavType.StringType })
        ) {
            CardScreen(
                it.arguments?.getString("deckId"),
                navController,
                it.arguments?.getString("deckTopic")
            )
        }
        composable("editCardScreen/{deckId}/{deckTopic}/{cardId}/{cardQuestion}/{cardAnswer}",
            arguments = listOf(
                navArgument("deckId") { type = NavType.StringType },
                navArgument("deckTopic") { type = NavType.StringType },
                navArgument("cardId") { type = NavType.StringType },
                navArgument("cardQuestion") { type = NavType.StringType },
                navArgument("cardAnswer") { type = NavType.StringType })
        ) {
            EditCardScreen(
                it.arguments?.getString("deckId"),
                it.arguments?.getString("deckTopic"),
                it.arguments?.getString("cardId"),
                navController,
                it.arguments?.getString("cardQuestion"),
                it.arguments?.getString("cardAnswer")
            )
        }
    }
}

