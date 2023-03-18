package com.example.myapplication

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.myapplication.entities.UserPracticeSettings
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen (navController: NavController, context: Context, lifecycleCoroutineScope: LifecycleCoroutineScope, userPracticeSettings: UserPracticeSettings) {
    val settingsDao = AppDatabase.getInstance(context).settingsDao
    var userPracticeSettings by remember { mutableStateOf(userPracticeSettings)}

    runBlocking {
        userPracticeSettings = settingsDao.getUserPracticeSettings().first()
    }

//    This will crash if you switch off all tense switches, need to sort this.

    var reflexiveVerbsEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showReflexiveVerbs) }
    var uncommonVerbsEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showUncommonVerbs) }
    var presentTenseEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showPresentTense) }
    var preteriteTenseEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showPreteriteTense) }
    var futureTenseEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showFutureTense) }
    var imperfectTenseEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showImperfectTense) }
    var conditionalTenseEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showConditionalTense) }

    var presentPerfectTenseEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showPresentPerfect) }
    var preteritePerfectTenseEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showPreteritePerfect) }
    var futurePerfectTenseEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showFuturePerfect) }
    var conditionalPerfectTenseEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showConditionalPerfect) }
    var pluperfectTenseEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showPluperfect) }

    var presentSubjunctiveTenseEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showPresentSubjunctive) }
    var imperfectSubjunctiveRaTenseEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showImperfectSubjunctiveRa) }
    var imperfectSubjunctiveSeTenseEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showImperfectSubjunctiveSe) }

    var imperativeTenseEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showImperative) }
    var negativeImperativeTenseEnabled by rememberSaveable { mutableStateOf(userPracticeSettings.showNegativeImperative) }



    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Settings") },
                modifier = Modifier,
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate("practice")
                        }
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back arrow")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface
                ),
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(canScroll = { true })
            )
        },
        content = { innerPadding -> WindowInsets.navigationBars.asPaddingValues()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 4.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 6.dp, vertical = 14.dp)
                ) {
                    Text(
                        text = "Verbs",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {Text("Reflexive verbs")},
                    trailingContent =
                    {
                        Switch(
                            checked = reflexiveVerbsEnabled,
                            onCheckedChange = { isChecked ->
                                reflexiveVerbsEnabled = isChecked
                                if (isChecked) {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showReflexiveVerbs = true
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                } else {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showReflexiveVerbs = false
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                }
                            }
                        )
                    }
                )
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {Text("Uncommon verbs")},
                    trailingContent =
                    {
                        Switch(
                            checked = uncommonVerbsEnabled,
                            onCheckedChange = { isChecked ->
                                uncommonVerbsEnabled = isChecked
                                if (isChecked) {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showUncommonVerbs = true
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                } else {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showUncommonVerbs = false
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                }
                            }
                        )
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 6.dp, vertical = 14.dp)
                ) {
                    Text(
                        text = "Indicative tenses",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = { Text("Present tense")},
                    trailingContent =
                    {
                        Switch(
                        checked = presentTenseEnabled,
                        onCheckedChange = { isChecked ->
                            presentTenseEnabled = isChecked
                            if (isChecked) {
                                val newUserPracticeSettings = userPracticeSettings.copy()
                                newUserPracticeSettings.showPresentTense = true
                                lifecycleCoroutineScope.launch {
                                    updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                    userPracticeSettings = getUserPracticeSettings(context,lifecycleCoroutineScope)
                                }
                            } else {
                                val newUserPracticeSettings = userPracticeSettings.copy()
                                newUserPracticeSettings.showPresentTense = false
                                lifecycleCoroutineScope.launch {
                                    updateUserPracticeSettings(
                                        context,
                                        lifecycleCoroutineScope,
                                        newUserPracticeSettings
                                    )
                                    userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)

                                }
                            }
                        }
                        )
                    }
                )
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {Text("Preterite tense")},
                    trailingContent =
                    {
                        Switch(
                            checked = preteriteTenseEnabled,
                            onCheckedChange = { isChecked ->
                                preteriteTenseEnabled = isChecked
                                if (isChecked) {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showPreteriteTense = true
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                } else {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showPreteriteTense = false
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                }
                            }
                        )
                    }
                )
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {Text("Future tense")},
                    trailingContent =
                    {
                        Switch(
                            checked = futureTenseEnabled,
                            onCheckedChange = { isChecked ->
                                futureTenseEnabled = isChecked
                                if (isChecked) {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showFutureTense = true
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                } else {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showFutureTense = false
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                }
                            }
                        )
                    }
                )
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {Text("Imperfect tense")},
                    trailingContent =
                    {
                        Switch(
                            checked = imperfectTenseEnabled,
                            onCheckedChange = { isChecked ->
                                imperfectTenseEnabled = isChecked
                                if (isChecked) {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showImperfectTense = true
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                } else {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showImperfectTense = false
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                }
                            }
                        )
                    }
                )
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {Text("Conditional tense")},
                    trailingContent =
                    {
                        Switch(
                            checked = conditionalTenseEnabled,
                            onCheckedChange = { isChecked ->
                                conditionalTenseEnabled = isChecked
                                if (isChecked) {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showConditionalTense = true
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                } else {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showConditionalTense = false
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                }
                            }
                        )
                    }
                )
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {Text("Present perfect tense")},
                    trailingContent =
                    {
                        Switch(
                            checked = presentPerfectTenseEnabled,
                            onCheckedChange = { isChecked ->
                                presentPerfectTenseEnabled = isChecked
                                if (isChecked) {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showPresentPerfect = true
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                } else {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showPresentPerfect = false
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                }
                            }
                        )
                    }
                )
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {Text("Preterite perfect tense")},
                    trailingContent =
                    {
                        Switch(
                            checked = preteritePerfectTenseEnabled,
                            onCheckedChange = { isChecked ->
                                preteritePerfectTenseEnabled = isChecked
                                if (isChecked) {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showPreteritePerfect = true
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                } else {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showPreteritePerfect = false
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                }
                            }
                        )
                    }
                )
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {Text("Future perfect tense")},
                    trailingContent =
                    {
                        Switch(
                            checked = futurePerfectTenseEnabled,
                            onCheckedChange = { isChecked ->
                                futurePerfectTenseEnabled = isChecked
                                if (isChecked) {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showFuturePerfect = true
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                } else {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showFuturePerfect = false
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                }
                            }
                        )
                    }
                )
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {Text("Conditional perfect tense")},
                    trailingContent =
                    {
                        Switch(
                            checked = conditionalPerfectTenseEnabled,
                            onCheckedChange = { isChecked ->
                                conditionalPerfectTenseEnabled = isChecked
                                if (isChecked) {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showConditionalPerfect = true
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                } else {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showConditionalPerfect = false
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                }
                            }
                        )
                    }
                )
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {Text("Pluperfect tense")},
                    trailingContent =
                    {
                        Switch(
                            checked = pluperfectTenseEnabled,
                            onCheckedChange = { isChecked ->
                                pluperfectTenseEnabled = isChecked
                                if (isChecked) {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showPluperfect = true
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                } else {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showPluperfect = false
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                }
                            }
                        )
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 6.dp, vertical = 14.dp)
                ) {
                    Text(
                        text = "Subjunctive tenses",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {Text("Present subjunctive tense")},
                    trailingContent =
                    {
                        Switch(
                            checked = presentSubjunctiveTenseEnabled,
                            onCheckedChange = { isChecked ->
                                presentSubjunctiveTenseEnabled = isChecked
                                if (isChecked) {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showPresentSubjunctive = true
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                } else {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showPresentSubjunctive = false
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                }
                            }
                        )
                    }
                )
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {Text("Imperfect subjunctive tense (Ra)")},
                    trailingContent =
                    {
                        Switch(
                            checked = imperfectSubjunctiveRaTenseEnabled,
                            onCheckedChange = { isChecked ->
                                imperfectSubjunctiveRaTenseEnabled = isChecked
                                if (isChecked) {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showImperfectSubjunctiveRa = true
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                } else {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showImperfectSubjunctiveRa = false
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                }
                            }
                        )
                    }
                )
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {Text("Imperfect Subjunctive tense (Se)")},
                    trailingContent =
                    {
                        Switch(
                            checked = imperfectSubjunctiveSeTenseEnabled,
                            onCheckedChange = { isChecked ->
                                imperfectSubjunctiveSeTenseEnabled = isChecked
                                if (isChecked) {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showImperfectSubjunctiveSe = true
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                } else {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showImperfectSubjunctiveSe = false
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                }
                            }
                        )
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 6.dp, vertical = 14.dp)
                ) {
                    Text(
                        text = "Imperative moods",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {Text("Imperative tense")},
                    trailingContent =
                    {
                        Switch(
                            checked = imperativeTenseEnabled,
                            onCheckedChange = { isChecked ->
                                imperativeTenseEnabled = isChecked
                                if (isChecked) {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showImperative = true
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                } else {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showImperative = false
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                }
                            }
                        )
                    }
                )
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {Text("Negative Imperative tense")},
                    trailingContent =
                    {
                        Switch(
                            checked = negativeImperativeTenseEnabled,
                            onCheckedChange = { isChecked ->
                                negativeImperativeTenseEnabled = isChecked
                                if (isChecked) {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showNegativeImperative = true
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                } else {
                                    val newUserPracticeSettings = userPracticeSettings.copy()
                                    newUserPracticeSettings.showNegativeImperative = false
                                    lifecycleCoroutineScope.launch {
                                        updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                                        userPracticeSettings = getUserPracticeSettings(context, lifecycleCoroutineScope)
                                    }
                                }
                            }
                        )
                    }
                )
            }
        }
    )
}

suspend fun updateUserPracticeSettings(
    context: Context,
    lifecycleCoroutineScope: LifecycleCoroutineScope,
    userPracticeSettings: UserPracticeSettings
) {
    val settingsDao = AppDatabase.getInstance(context).settingsDao
    return settingsDao.updateUserPracticeSettings(userPracticeSettings)
}

suspend fun getUserPracticeSettings(context: Context, lifecycleCoroutineScope: LifecycleCoroutineScope) : UserPracticeSettings {
    val settingsDao = AppDatabase.getInstance(context).settingsDao
    return settingsDao.getUserPracticeSettings().first()
}

//Need: All tenses, Irregular, Reflexive, Common