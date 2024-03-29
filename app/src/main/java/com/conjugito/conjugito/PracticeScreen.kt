package com.conjugito.conjugito

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.conjugito.conjugito.entities.UserPracticeSettings
import com.conjugito.conjugito.entities.listOfTensesStrings
import com.conjugito.conjugito.entities.*
import com.conjugito.conjugito.entities.relations.VerbAndPresent
import com.conjugito.conjugito.entities.relations.VerbAndTense
import com.conjugito.conjugito.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import android.content.res.AssetManager
import com.google.android.play.core.assetpacks.AssetPackManagerFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.FileDescriptor
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.text.Normalizer
import java.text.Normalizer.normalize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeScreen(navController: NavController, context: Context, lifecycleCoroutineScope: LifecycleCoroutineScope, listOfVerbs: List<Verb>, userPracticeSettings: UserPracticeSettings) {

    val settingsDao = AppDatabase.getInstance(context).settingsDao
    var listOfVerbs = listOfVerbs
    var userPracticeSettings: UserPracticeSettings
    var listOfTenses by remember { mutableStateOf(listOfTensesStrings) }
    val systemUiController = rememberSystemUiController()

    runBlocking {
        userPracticeSettings = settingsDao.getUserPracticeSettings().first()
        listOfVerbs = updateListOfVerbs(userPracticeSettings, listOfVerbs)
        listOfTenses = updateListOfTenses(userPracticeSettings)
        delay(100)
        if(listOfTenses.isEmpty()){
            val newUserPracticeSettings = userPracticeSettings.copy()
            newUserPracticeSettings.showPresentTense = true
            lifecycleCoroutineScope.launch {
                updateUserPracticeSettings(context, lifecycleCoroutineScope, newUserPracticeSettings)
                userPracticeSettings = getUserPracticeSettings(context,lifecycleCoroutineScope)
            }
        }
    }

    systemUiController.setStatusBarColor(
        color = MaterialTheme.colorScheme.surface
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Conjugations") },
                modifier = Modifier,
                actions = {
                    TextButton(
                        onClick = { navController.navigate("verbs") },
                        colors = ButtonDefaults.textButtonColors( contentColor = MaterialTheme.colorScheme.onSurface )
                    ) {
                        Text("Verbs", style = MaterialTheme.typography.titleMedium)
                    }
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.surface,
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
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Practice(context, lifecycleCoroutineScope, listOfVerbs, listOfTenses, navController)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Practice    (context: Context, lifecycleCoroutineScope: LifecycleCoroutineScope, listOfVerbs: List<Verb>, listOfTenses: List<String>, navController: NavController) {
    val listOfVerbForms = mutableListOf(
        "yo",
        "yo",
        "yo",
        "tú",
        "tú",
        "tú",
        "él",
        "ella",
        "usted",
        "nosotros",
        "nosotros",
        "nosotros",
        "vosotros",
        "vosotros",
        "vosotros",
        "ellos",
        "ellas",
        "ustedes"
    )
    var verbForm by remember { mutableStateOf(listOfVerbForms.random()) }
    var englishInfinitive by remember { mutableStateOf("") }
    var rowColour by remember { mutableStateOf(Color.Transparent) }
    var correctIcon by remember { mutableStateOf(Icons.Default.Clear) }
    var checkedUserAnswer by remember { mutableStateOf("unchecked") }
    var checkNextButtonText by remember { mutableStateOf("Check") }
    var hideAnswerTextField by remember { mutableStateOf(false) }
    var answer by remember { mutableStateOf("") }
    var correctAnswer by remember { mutableStateOf("") }
    var verb by remember { mutableStateOf(listOfVerbs.random()) }
    var tense by remember { mutableStateOf(listOfTenses.random()) }
    var verbAndTense by remember {
        mutableStateOf(
            refreshVerb(
                verb,
                tense,
                lifecycleCoroutineScope,
                context
            )
        )
    }
    val focusRequester = remember { FocusRequester() }
    var isTenseModalOpen by remember { mutableStateOf(false) }
    var showMeEnabled by remember { mutableStateOf(true) }
    var checkEnabled by remember { mutableStateOf(false) }

    fun playAudio(context: Context, audioFilePath: String) {
        val mediaPlayer = MediaPlayer()
        val assetManager: AssetManager = context.assets
        try {
            assetManager.openFd(audioFilePath).use { afd ->
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer.reset()
                }
                mediaPlayer.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                mediaPlayer.prepare()
                mediaPlayer.start()
                mediaPlayer.setOnCompletionListener {
                    it.release()
                }
            }
        } catch (e: Exception) {
            // Handle exceptions such as IOException or IllegalArgumentException
            e.printStackTrace()
        }
    }


    fun playAudioForInfinitive(context: Context, infinitive: String) {
        playAudio(context, "$infinitive/$infinitive.mp3")
    }

    fun playAudioForConjugation(context: Context, infinitive: String, tense: String, verbForm: String) {
        val formattedTense = tense.replace("Tense", "")
            .replaceFirstChar { it.uppercaseChar() }
        val formattedVerbForm = when (verbForm) {
            "tú" -> "tu"
            "él", "ella", "usted" -> "el"
            "ellos", "ellas", "ustedes" -> "ellos"
            else -> verbForm // or some default value if verbForm doesn't match any case
        }
        playAudio(context, "$infinitive/$formattedTense/$formattedVerbForm.mp3")
    }

    if (isTenseModalOpen) {
        tenseDescriptions[tense]?.let {
            TenseModal(
                tense = tense,
                tenseDescription = it,
                onClose = { isTenseModalOpen = false }
            )
        }
    }

    //Imperative tenses never have a yo form, this stops "Yo" showing for imperative tenses
    if ((tense == "imperativeTense" || tense == "negativeImperativeTense") && verbForm == "yo") {
        verbForm = (listOfVerbForms - "yo" - "yo" - "yo").random()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 50.dp)
    ) {
        val correctAnswer = getCorrectAnswer(verbForm, verbAndTense)

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.width(36.dp))

                Text(
                    text = verb.infinitive,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.clickable { navController.navigate("verb/${verb.infinitive}") }
                )

                Spacer(modifier = Modifier.width(3.dp)) // Space between text and icon

                IconButton(onClick = { playAudioForInfinitive(context, verb.infinitive) }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.VolumeUp, contentDescription = "Play Verb Sound")
                }
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            if (checkNextButtonText == "Next") {
                Text(
                    text = verb.englishInfinitive,
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        rowColour = when (checkedUserAnswer) {
            "unchecked" -> Color.Transparent
            "correct" -> Green404.copy(0.19f)
            "incorrect" -> MaterialTheme.colorScheme.errorContainer.copy(0.45f)
            "shown" -> Color.Transparent
            else -> Color.Transparent
        }
        correctIcon = when (checkedUserAnswer) {
            "unchecked" -> Icons.Default.Clear
            "correct" -> Icons.AutoMirrored.Filled.VolumeUp
            "incorrect" -> Icons.Default.Close
            "shown" -> Icons.AutoMirrored.Filled.VolumeUp
            else -> Icons.Default.Clear
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            TensePill(
                tense = tense,
                onTensePillClicked = { isTenseModalOpen = true },
                large = false
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = answer,
            onValueChange = {
                answer = it
                getCorrectAnswer(verbForm, verbAndTense)
            },
            prefix = { Text(text = verbForm) },
            singleLine = true,
            enabled = !hideAnswerTextField,
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (checkedUserAnswer == "incorrect") {
                            answer = ""
                            checkedUserAnswer = "unchecked"
                        }
                        else if (checkedUserAnswer == "correct") {
                            playAudioForConjugation(context, verb.infinitive, tense, verbForm)
                        }
                        else if (checkedUserAnswer == "shown") {
                            playAudioForConjugation(context, verb.infinitive, tense, verbForm)
                        }
                    }
                ) {
                    Icon(
                        imageVector = correctIcon,
                        contentDescription = null,
                        tint = when (checkedUserAnswer) {
                            "unchecked" -> Color.Transparent
                            "correct" -> MaterialTheme.colorScheme.onSurface
                            "incorrect" -> Color.Red
                            "shown" -> MaterialTheme.colorScheme.onSurface
                            else -> Color.Transparent
                        }
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = rowColour,
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedPrefixColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            textStyle = when (tense) {
                "presentPerfectTense" -> MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center)
                "preteritePerfectTense" -> MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center)
                "futurePerfectTense" -> MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center)
                "conditionalPerfectTense" -> MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center)
                "pluperfectTense" -> MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center)
                else -> MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center)
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    if (checkNextButtonText == "Check") {
                        if (checkAnswer(answer, correctAnswer)) {
                            checkedUserAnswer = "correct"
                            answer = correctAnswer
                            verbAndTense = refreshVerb(
                                verb,
                                tense,
                                lifecycleCoroutineScope,
                                context
                            )
                            checkNextButtonText = "Next"
                            showMeEnabled = false
                            hideAnswerTextField = true
                            englishInfinitive = verb.englishInfinitive
                        } else {
                            checkedUserAnswer = "incorrect"
                        }
                    } else {
                        answer = ""
                        verb = listOfVerbs.random()
                        verbForm = listOfVerbForms.random()
                        tense = listOfTenses.random()
                        verbAndTense =
                            refreshVerb(verb, tense, lifecycleCoroutineScope, context)
                        checkNextButtonText = "Check"
                        showMeEnabled = true
                        checkedUserAnswer = "unchecked"
                        hideAnswerTextField = false
                        englishInfinitive = ""
                        lifecycleCoroutineScope.launch {
                            delay(100)
                            focusRequester.requestFocus()
                        }
                    }

                }
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(enabled = showMeEnabled,
                onClick = {
                    checkNextButtonText = "Next"
                    checkedUserAnswer = "shown"
                    hideAnswerTextField = true
                    showMeEnabled = false
                    verbAndTense =
                        refreshVerb(verb, tense, lifecycleCoroutineScope, context)
                    answer = correctAnswer
                    englishInfinitive = verb.englishInfinitive

                }) {
                Text("Show me")
            }
            checkEnabled = (answer != "" || checkNextButtonText == "Next")

            Button(
                enabled = checkEnabled,
                onClick = {
                    if (checkNextButtonText == "Check") {
                        if (checkAnswer(answer, correctAnswer)) {
                            checkedUserAnswer = "correct"
                            answer = correctAnswer
                            verbAndTense = refreshVerb(
                                verb,
                                tense,
                                lifecycleCoroutineScope,
                                context
                            )
                            checkNextButtonText = "Next"
                            showMeEnabled = false
                            hideAnswerTextField = true
                            englishInfinitive = verb.englishInfinitive
                        } else {
                            checkedUserAnswer = "incorrect"
                        }
                    } else {
                        answer = ""
                        verb = listOfVerbs.random()
                        verbForm = listOfVerbForms.random()
                        tense = listOfTenses.random()
                        verbAndTense =
                            refreshVerb(verb, tense, lifecycleCoroutineScope, context)
                        checkNextButtonText = "Check"
                        showMeEnabled = true
                        checkedUserAnswer = "unchecked"
                        hideAnswerTextField = false
                        englishInfinitive = ""
                        lifecycleCoroutineScope.launch {
                            delay(100)
                            focusRequester.requestFocus()
                        }
                    }
                }) {
                Text(checkNextButtonText)
            }
        }
    }
}

private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

fun CharSequence.unaccent(): String {
    val temp = normalize(this, Normalizer.Form.NFD)
    return REGEX_UNACCENT.replace(temp, "")
}

fun checkAnswer(answer: String, correctAnswer: String): Boolean {
    val cleanedAnswer = answer.trim().lowercase().unaccent()
    val cleanedCorrectAnswer = correctAnswer.unaccent()

    return cleanedAnswer == cleanedCorrectAnswer
}

fun getCorrectAnswer(verbForm: String, verbAndTense: VerbAndTense): String = when (verbForm) {
    "yo" -> verbAndTense.yo
    "tú" -> verbAndTense.tu
    "él" -> verbAndTense.el
    "ella" -> verbAndTense.el
    "usted" -> verbAndTense.el
    "nosotros" -> verbAndTense.nosotros
    "vosotros" -> verbAndTense.vosotros
    "ellos" -> verbAndTense.ellos
    "ellas" -> verbAndTense.ellos
    "ustedes" -> verbAndTense.ellos
    else -> throw IllegalArgumentException("Invalid verb form")
}

fun refreshVerb (verb: Verb, tense: String, lifecycleCoroutineScope: LifecycleCoroutineScope, context: Context): VerbAndTense {
    val verbDao = AppDatabase.getInstance(context).verbDao
    val verb = verb
    val tense = tense
    var verbAndTense : VerbAndTense

    runBlocking {
        verbAndTense = when (tense) {
            "presentTense" -> verbDao.getVerbAndPresentWithInfinitive(verb.infinitive).first()
            "preteriteTense" -> verbDao.getVerbAndPreteriteWithInfinitive(verb.infinitive).first()
            "futureTense" -> verbDao.getVerbAndFutureWithInfinitive(verb.infinitive).first()
            "imperfectTense" -> verbDao.getVerbAndImperfectWithInfinitive(verb.infinitive).first()
            "conditionalTense" -> verbDao.getVerbAndConditionalWithInfinitive(verb.infinitive).first()
            "presentPerfectTense" -> verbDao.getVerbAndPresentPerfectWithInfinitive(verb.infinitive).first()
            "preteritePerfectTense" -> verbDao.getVerbAndPreteritePerfectWithInfinitive(verb.infinitive).first()
            "futurePerfectTense" -> verbDao.getVerbAndFuturePerfectWithInfinitive(verb.infinitive).first()
            "conditionalPerfectTense" -> verbDao.getVerbAndConditionalPerfectWithInfinitive(verb.infinitive).first()
            "pluperfectTense" -> verbDao.getVerbAndPluperfectWithInfinitive(verb.infinitive).first()
            "imperativeTense" -> verbDao.getVerbAndImperativeWithInfinitive(verb.infinitive).first()
            "negativeImperativeTense" -> verbDao.getVerbAndNegativeImperativeWithInfinitive(verb.infinitive).first()
            "presentSubjunctiveTense" -> verbDao.getVerbAndPresentSubjunctive(verb.infinitive).first()
            "imperfectSubjunctiveRaTense" -> verbDao.getVerbAndImperfectSubjunctiveRa(verb.infinitive).first()
            "imperfectSubjunctiveSeTense" -> verbDao.getVerbAndImperfectSubjunctiveSe(verb.infinitive).first()

            else -> {
                VerbAndPresent(verb,Present("hablar","hablo","hablas","habla","hablamos","hablais","hablan"))
            }
        }
    }
    return verbAndTense
}

@Composable
fun TensePill(tense: String, onTensePillClicked: () -> Unit, large: Boolean) {
    val isDarkMode = isSystemInDarkTheme()
    val tensePillColour = when (tense) {
        "presentTense" -> if (isDarkMode) Present30 else Present90
        "preteriteTense" -> if (isDarkMode) Preterite30 else Preterite90
        "futureTense" -> if (isDarkMode) Future30 else Future90
        "imperfectTense" -> if (isDarkMode) Imperfect30 else Imperfect90
        "conditionalTense" -> if (isDarkMode) Conditional30 else Conditional90
        "presentPerfectTense" -> if (isDarkMode) PresentPerfect30 else PresentPerfect90
        "preteritePerfectTense" -> if (isDarkMode) Preterite80 else Preterite40
        "futurePerfectTense" -> if (isDarkMode) Future80 else Future40
        "conditionalPerfectTense" -> if (isDarkMode) Conditional80 else Conditional40
        "pluperfectTense" -> if (isDarkMode) Pluperfect30 else Pluperfect90
        "imperativeTense" -> if (isDarkMode) Imperative30 else Imperative90
        "negativeImperativeTense" -> if (isDarkMode) NegativeImperative30 else NegativeImperative90
        "presentSubjunctiveTense" -> if (isDarkMode) Present80 else Present40
        "imperfectSubjunctiveRaTense" -> if (isDarkMode) Future80 else Future40
        "imperfectSubjunctiveSeTense" -> if (isDarkMode) Conditional80 else Conditional40
        else -> MaterialTheme.colorScheme.tertiaryContainer
    }
    val tensePillModifier = if (large) {
        Modifier
            .padding(horizontal = 18.dp, vertical = 12.dp)
            .background(tensePillColour, RoundedCornerShape(48.dp))
            .padding(horizontal = 18.dp, vertical = 6.dp)
    } else {
        Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(tensePillColour, RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    }
    Box(
        modifier = tensePillModifier
            .clickable(
                onClick = { onTensePillClicked() }
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            when (tense) {
                "presentTense" -> Text(
                text = "Present",
                color = if (isDarkMode) Present90 else Present10,
                style = if (large) MaterialTheme.typography.titleLarge else MaterialTheme.typography.labelMedium
                )
                "preteriteTense" -> Text(
                text = "Preterite",
                color = if (isDarkMode) Preterite90 else Preterite10,
                style = if (large) MaterialTheme.typography.titleLarge else MaterialTheme.typography.labelMedium
                )
                "futureTense" -> Text(
                text = "Future",
                color = if (isDarkMode) Future90 else Future10,
                style = if (large) MaterialTheme.typography.titleLarge else MaterialTheme.typography.labelMedium
                )
                "imperfectTense" -> Text(
                text = "Imperfect",
                color = if (isDarkMode) Imperfect90 else Imperfect10,
                style = if (large) MaterialTheme.typography.titleLarge else MaterialTheme.typography.labelMedium
                )
                "conditionalTense" -> Text(
                text = "Conditional",
                color = if (isDarkMode) Conditional90 else Conditional10,
                style = if (large) MaterialTheme.typography.titleLarge else MaterialTheme.typography.labelMedium
                )
                "presentPerfectTense" -> Text(
                text = "Present Perfect",
                color = if (isDarkMode) PresentPerfect90 else PresentPerfect10,
                style = if (large) MaterialTheme.typography.titleLarge else MaterialTheme.typography.labelMedium
                )
                "preteritePerfectTense" -> Text(
                text = "Preterite Perfect",
                color = if (isDarkMode) Preterite20 else Preterite100,
                style = if (large) MaterialTheme.typography.titleLarge else MaterialTheme.typography.labelMedium
                )
                "futurePerfectTense" -> Text(
                text = "Future Perfect",
                color = if (isDarkMode) Future20 else Future100,
                    style = if (large) MaterialTheme.typography.titleLarge else MaterialTheme.typography.labelMedium
                )
                "conditionalPerfectTense" -> Text(
                text = "Conditional Perfect",
                color = if (isDarkMode) Conditional20 else Conditional100,
                style = if (large) MaterialTheme.typography.titleLarge else MaterialTheme.typography.labelMedium
                )
                "pluperfectTense" -> Text(
                text = "Pluperfect",
                color = if (isDarkMode) Pluperfect90 else Pluperfect10,
                style = if (large) MaterialTheme.typography.titleLarge else MaterialTheme.typography.labelMedium
                )
                "imperativeTense" -> Text(
                text = "Imperative",
                color = if (isDarkMode) Imperative90 else Imperative10,
                style = if (large) MaterialTheme.typography.titleLarge else MaterialTheme.typography.labelMedium
                )
                "negativeImperativeTense" -> Text(
                text = "Negative Imperative",
                color = if (isDarkMode) NegativeImperative90 else NegativeImperative10,
                style = if (large) MaterialTheme.typography.titleLarge else MaterialTheme.typography.labelMedium
            )
            "presentSubjunctiveTense" -> Text(
                text = "Present Subjunctive",
                color = if (isDarkMode) Present20 else Present100,
                style = if (large) MaterialTheme.typography.titleLarge else MaterialTheme.typography.labelMedium
            )
            "imperfectSubjunctiveRaTense" -> Text(
                text = "Imperfect Subjunctive (Ra)",
                color = if (isDarkMode) Future20 else Future100,
                style = if (large) MaterialTheme.typography.titleLarge else MaterialTheme.typography.labelMedium
            )
            "imperfectSubjunctiveSeTense" -> Text(
                text = "Imperfect Subjunctive (Se)",
                color = if (isDarkMode) Conditional20 else Conditional100,
                style = if (large) MaterialTheme.typography.titleLarge else MaterialTheme.typography.labelMedium
            )
            else -> "Error"
        }
            if(!large) {
                Icon(
                    imageVector = Icons.Default.Info,
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .size(10.dp),
                    contentDescription = "info",
                    tint = when (tense) {
                        "presentTense" -> if (isDarkMode) Present90 else Present10
                        "preteriteTense" -> if (isDarkMode) Preterite90 else Preterite10
                        "futureTense" -> if (isDarkMode) Future90 else Future10
                        "imperfectTense" -> if (isDarkMode) Imperfect90 else Imperfect10
                        "conditionalTense" -> if (isDarkMode) Conditional90 else Conditional10
                        "presentPerfectTense" -> if (isDarkMode) PresentPerfect90 else PresentPerfect10
                        "preteritePerfectTense" -> if (isDarkMode) Preterite20 else Preterite100
                        "futurePerfectTense" -> if (isDarkMode) Future20 else Future100
                        "conditionalPerfectTense" -> if (isDarkMode) Conditional20 else Conditional100
                        "pluperfectTense" -> if (isDarkMode) Pluperfect90 else Pluperfect10
                        "imperativeTense" -> if (isDarkMode) Imperative90 else Imperative10
                        "negativeImperativeTense" -> if (isDarkMode) NegativeImperative90 else NegativeImperative10
                        "presentSubjunctiveTense" -> if (isDarkMode) Present20 else Present100
                        "imperfectSubjunctiveRaTense" -> if (isDarkMode) Future20 else Future100
                        "imperfectSubjunctiveSeTense" -> if (isDarkMode) Conditional20 else Conditional100
                        else -> Green404
                    }
                )
            }
        }
    }
}

@Composable
fun TenseModal(
    tense: String,
    tenseDescription: String,
    onClose: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onClose,
        title = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                TensePill(
                    tense = tense,
                    onTensePillClicked = {},
                    large = true
                )
            }
        },
        text = {
            Text(
                text = tenseDescription,
            ) },
        confirmButton = {
            Button(
                onClick = onClose,
            )
            {
                Text("Dismiss")
            }
        }
    )
}

fun updateListOfVerbs (userPracticeSettings: UserPracticeSettings, listOfVerbs: List<Verb>) : List<Verb> {
    var listOfVerbs = listOfVerbs

    listOfVerbs = listOfVerbs.filter { verb ->
        (userPracticeSettings.showReflexiveVerbs || !verb.reflexive) &&
                (userPracticeSettings.showUncommonVerbs || verb.common)
    }
    return listOfVerbs
}

fun updateListOfTenses(userPracticeSettings: UserPracticeSettings): MutableList<String> {
    val tenseMap = mapOf(
        "presentTense" to userPracticeSettings.showPresentTense,
        "preteriteTense" to userPracticeSettings.showPreteriteTense,
        "futureTense" to userPracticeSettings.showFutureTense,
        "imperfectTense" to userPracticeSettings.showImperfectTense,
        "conditionalTense" to userPracticeSettings.showConditionalTense,
        "presentPerfectTense" to userPracticeSettings.showPresentPerfect,
        "preteritePerfectTense" to userPracticeSettings.showPreteritePerfect,
        "futurePerfectTense" to userPracticeSettings.showFuturePerfect,
        "conditionalPerfectTense" to userPracticeSettings.showConditionalPerfect,
        "pluperfectTense" to userPracticeSettings.showPluperfect,
        "imperativeTense" to userPracticeSettings.showImperative,
        "negativeImperativeTense" to userPracticeSettings.showNegativeImperative,
        "presentSubjunctiveTense" to userPracticeSettings.showPresentSubjunctive,
        "imperfectSubjunctiveRaTense" to userPracticeSettings.showImperfectSubjunctiveRa,
        "imperfectSubjunctiveSeTense" to userPracticeSettings.showImperfectSubjunctiveSe
    )

    // Filter the map to get only the tenses to display
    return tenseMap.filterValues { it }.keys.toList().toMutableList()
}