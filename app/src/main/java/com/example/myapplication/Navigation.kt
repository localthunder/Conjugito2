package com.example.myapplication

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.myapplication.entities.UserPracticeSettings
import com.example.myapplication.entities.Verb
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation (listOfVerbs: List<Verb>, userPracticeSettings: UserPracticeSettings, lifecycleCoroutineScope: LifecycleCoroutineScope) {
    val lifecycleCoroutineScope = lifecycleCoroutineScope
    val navController = rememberAnimatedNavController()
    val listOfVerbs = listOfVerbs
    val context = LocalContext.current
    AnimatedNavHost(navController = navController, startDestination = "practice") {
        composable(
            "practice"
        ) {
            PracticeScreen(
                navController,
                context,
                lifecycleCoroutineScope,
                listOfVerbs,
                userPracticeSettings
            )
        }
        composable(
            "settings",
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(500),
                    initialOffsetX = { fullWidth -> fullWidth }
                )
            },
            exitTransition = {
                slideOutHorizontally (
                    animationSpec = tween(800),
                    targetOffsetX = { fullWidth -> fullWidth }
                )
            }
        ) {
            SettingsScreen(
                navController,
                context,
                lifecycleCoroutineScope,
                userPracticeSettings
            )
        }
        composable(
            "verbs",
            exitTransition = {
                slideOutHorizontally (
                    animationSpec = tween(800),
                    targetOffsetX = { fullWidth -> fullWidth }
                )
            }
        ) { VerbsScreen(navController) }
        composable(
            "verb/{infinitive}",
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(500),
                    initialOffsetX = { fullWidth -> fullWidth }
                )
            },
            exitTransition = {
                slideOutHorizontally (
                    animationSpec = tween(800),
                    targetOffsetX = { fullWidth -> fullWidth }
                )
            }
        ) { backStackEntry ->
            val infinitive = backStackEntry.arguments?.getString("infinitive")
            val verb = listOfVerbs.find { it.infinitive == infinitive }
            verb?.let { nonNullVerb ->
                VerbScreen(nonNullVerb, navController, context)
            }
        }
    }
}