package com.conjugito.conjugito

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import com.conjugito.conjugito.entities.UserPracticeSettings
import com.conjugito.conjugito.entities.Verb
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
            "practice",
            exitTransition = {
                when (targetState.destination.route) {
                    "verb/{infinitive}" ->
                        fadeOut(
                            animationSpec = tween(700)
                        )
                    else -> null
                }
            }        ) {
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
                    animationSpec = tween(700),
                    initialOffsetX = { fullWidth: Int -> fullWidth }
                ) + fadeIn(
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutHorizontally (
                    animationSpec = tween(700),
                    targetOffsetX = { fullWidth: Int -> fullWidth }
                ) + fadeOut(
                    animationSpec = tween(700)
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
                when (targetState.destination.route) {
                    "practice" ->
                        slideOutHorizontally (
                            animationSpec = tween(1000),
                            targetOffsetX = { fullWidth: Int -> fullWidth }
                        ) + fadeOut(
                            animationSpec = tween(1000)
                        )
                    else -> null
                }
            }
        ) { VerbsScreen(navController) }
        composable(
            "verb/{infinitive}",
            enterTransition = {
                when (initialState.destination.route) {
                    "practice" -> fadeIn(
                        animationSpec = tween(700),
                    )
                    else -> slideInHorizontally(
                        animationSpec = tween(700),
                        initialOffsetX = { fullWidth: Int -> fullWidth }
                    ) + fadeIn(
                        animationSpec = tween(700)
                    )
                }
            },
            exitTransition = {
                slideOutHorizontally (
                    animationSpec = tween(700),
                    targetOffsetX = { fullWidth: Int -> fullWidth }
                ) + fadeOut(
                    animationSpec = tween(700)
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