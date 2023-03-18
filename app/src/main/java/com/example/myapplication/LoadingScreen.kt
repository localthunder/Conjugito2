package com.example.myapplication

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.myapplication.entities.*
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen (context: Context, lifecycleCoroutineScope: LifecycleCoroutineScope) {
    var isLoading by remember { mutableStateOf(true) }
    val verbDao = AppDatabase.getInstance(context).verbDao
    var listOfVerbs by remember { mutableStateOf (listOf<Verb>()) }
    val defaultUserPracticeSettings = UserPracticeSettings()
    var userPracticeSettings by remember { mutableStateOf(defaultUserPracticeSettings) }

        LaunchedEffect(Unit) {
            // delay the initialization of myVariable until loading is finished
            listOfVerbs = verbDao.getVerbs()
            userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
            delay(500)
            isLoading = false
        }
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Navigation(
                listOfVerbs = listOfVerbs,
                userPracticeSettings = userPracticeSettings,
                lifecycleCoroutineScope = lifecycleCoroutineScope
            )
        }
}