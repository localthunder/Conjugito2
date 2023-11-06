package com.conjugito.conjugito

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.conjugito.conjugito.entities.Present
import com.conjugito.conjugito.entities.Verb
import com.conjugito.conjugito.entities.listOfTensesStrings
import com.conjugito.conjugito.entities.relations.VerbAndPresent
import com.conjugito.conjugito.entities.relations.VerbAndTense
import com.conjugito.conjugito.entities.*
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerbScreen (verb: Verb, navController: NavController, context: Context) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {  },
                modifier = Modifier,
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
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
        content = { innerPadding ->
            WindowInsets.navigationBars.asPaddingValues()
            val verbAndPresent = runBlocking {
                val verbDao = AppDatabase.getInstance(context).verbDao
                verbDao.getVerbAndPresentWithInfinitive(verb.infinitive).first()
            }
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                val (expandedTenses, setExpandedTenses) = remember { mutableStateOf(setOf<String>()) }

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = verb.infinitive,
                        style = MaterialTheme.typography.displaySmall
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 36.dp)
                        .height(44.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = verb.englishInfinitive,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 18.dp, vertical = 14.dp)
                ) {
                    Text(
                        text = "Present",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(horizontal = 18.dp)
                ) {
                    ListItem(
                        headlineContent = { Text(
                            text = "yo",
                            style = MaterialTheme.typography.bodySmall
                        )},
                        trailingContent = {Text(
                            text = verbAndPresent.yo,
                            style = MaterialTheme.typography.bodyMedium
                        )}
                    )
                    ListItem(
                        headlineContent = { Text(
                            text = "tu",
                            style = MaterialTheme.typography.bodySmall
                        )},
                        trailingContent = { Text(
                            text = verbAndPresent.tu,
                            style = MaterialTheme.typography.bodyMedium
                        )}
                    )
                    ListItem(
                        headlineContent = { Text(
                            text = "el/ella/usted",
                            style = MaterialTheme.typography.bodySmall
                        )},
                        trailingContent = { Text(
                            text = verbAndPresent.el,
                            style = MaterialTheme.typography.bodyMedium
                        )}
                    )
                    ListItem(
                        headlineContent = { Text(
                            text = "nosotros",
                            style = MaterialTheme.typography.bodySmall
                        )},
                        trailingContent = {Text(
                            text = verbAndPresent.nosotros,
                            style = MaterialTheme.typography.bodyMedium
                        )}
                    )
                    ListItem(
                        headlineContent = { Text(
                            text = "vosotros",
                            style = MaterialTheme.typography.bodySmall
                        )},
                        trailingContent = {Text(
                            text = verbAndPresent.vosotros,
                            style = MaterialTheme.typography.bodyMedium
                        )}
                    )
                    ListItem(
                        headlineContent = { Text(
                            text = "ellos/ellas/ustedes",
                            style = MaterialTheme.typography.bodySmall
                        )},
                        trailingContent = {Text(
                            text = verbAndPresent.ellos,
                            style = MaterialTheme.typography.bodyMedium
                        )}
                    )
                }
                Divider()

                for (tense in listOfTensesStrings - "presentTense") {
                    ExpandableTenseHeader(
                        tense = tense.substringBefore("Tense").replaceFirstChar { it.uppercase() },
                        onClick = {
                            setExpandedTenses(
                                if (tense in expandedTenses) {
                                    expandedTenses - tense
                                } else {
                                    expandedTenses + tense
                                }
                            )
                        },
                        isExpanded = tense in expandedTenses
                    )
                    ExpandableListOfConjugations(
                        verbAndTense = runBlocking {
                            val verbDao = AppDatabase.getInstance(context).verbDao
                            when (tense) {
                                "presentTense" -> verbDao.getVerbAndPresentWithInfinitive(verb.infinitive)
                                    .first()
                                "preteriteTense" -> verbDao.getVerbAndPreteriteWithInfinitive(verb.infinitive)
                                    .first()
                                "futureTense" -> verbDao.getVerbAndFutureWithInfinitive(verb.infinitive)
                                    .first()
                                "imperfectTense" -> verbDao.getVerbAndImperfectWithInfinitive(verb.infinitive)
                                    .first()
                                "conditionalTense" -> verbDao.getVerbAndConditionalWithInfinitive(
                                    verb.infinitive
                                ).first()
                                "presentPerfectTense" -> verbDao.getVerbAndPresentPerfectWithInfinitive(
                                    verb.infinitive
                                ).first()
                                "preteritePerfectTense" -> verbDao.getVerbAndPreteritePerfectWithInfinitive(
                                    verb.infinitive
                                ).first()
                                "futurePerfectTense" -> verbDao.getVerbAndFuturePerfectWithInfinitive(
                                    verb.infinitive
                                ).first()
                                "conditionalPerfectTense" -> verbDao.getVerbAndConditionalPerfectWithInfinitive(
                                    verb.infinitive
                                ).first()
                                "PluperfectTense" -> verbDao.getVerbAndPluperfectWithInfinitive(verb.infinitive)
                                    .first()
                                "imperativeTense" -> verbDao.getVerbAndImperativeWithInfinitive(verb.infinitive)
                                    .first()
                                "negativeImperativeTense" -> verbDao.getVerbAndNegativeImperativeWithInfinitive(
                                    verb.infinitive
                                ).first()
                                "presentSubjunctiveTense" -> verbDao.getVerbAndPresentSubjunctive(
                                    verb.infinitive
                                ).first()
                                "imperfectSubjunctiveRaTense" -> verbDao.getVerbAndImperfectSubjunctiveRa(
                                    verb.infinitive
                                ).first()
                                "imperfectSubjunctiveSeTense" -> verbDao.getVerbAndImperfectSubjunctiveSe(
                                    verb.infinitive
                                ).first()


                                else -> {
                                    VerbAndPresent(
                                        verb,
                                        Present(
                                            "hablar",
                                            "hablo",
                                            "hablas",
                                            "habla",
                                            "hablamos",
                                            "hablais",
                                            "hablan"
                                        )
                                    )
                                }
                            }
                        },
                        isExpanded = tense in expandedTenses
                    )
                }
            }
        }
    )
}



@Composable
fun ExpandableTenseHeader(tense: String, isExpanded: Boolean, onClick: () -> Unit) {
    ListItem(modifier = Modifier
        .fillMaxWidth()
        .clickable(
            onClickLabel = "Expand tense to show list of conjugations for this $tense",
            onClick = onClick
        ),
        headlineContent = { Text(tense) },
        trailingContent = {
            val arrowDropUp = painterResource(id = R.drawable.ic_baseline_arrow_drop_up_24)
            if (isExpanded) {
                Icon(painter = arrowDropUp, contentDescription = "Collapse list of conjugations for $tense")
            } else {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Expand list of conjugations for $tense")
             }
        },
        colors = if (isExpanded) {
            ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                trailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                headlineColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            ListItemDefaults.colors()
        }
    )
    if(!isExpanded) {
        Divider()
    }
}

@Composable
fun ExpandableListOfConjugations(verbAndTense: VerbAndTense, isExpanded: Boolean) {
    // Opening Animation
    val expandTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(500)
        ) + fadeIn(
            animationSpec = tween(500)
        )
    }

    // Closing Animation
    val collapseTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(500)
        ) + fadeOut(
            animationSpec = tween(500)
        )
    }

    AnimatedVisibility(
        visible = isExpanded,
        enter = expandTransition,
        exit = collapseTransition
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 15.dp)
        ) {
            Column() {
                ListItem(
                    headlineContent = { Text(
                        text = "yo",
                        style = MaterialTheme.typography.bodySmall
                    )},
                    trailingContent = {Text(
                        text = verbAndTense.yo,
                        style = MaterialTheme.typography.bodyMedium
                    )}
                )
                ListItem(
                    headlineContent = { Text(
                        text = "tu",
                        style = MaterialTheme.typography.bodySmall
                    )},
                    trailingContent = {Text(
                        text = verbAndTense.tu,
                        style = MaterialTheme.typography.bodyMedium
                    )}
                )
                ListItem(
                    headlineContent = { Text(
                        text = "el/ella/usted",
                        style = MaterialTheme.typography.bodySmall
                    )},
                    trailingContent = {Text(
                        text = verbAndTense.el,
                        style = MaterialTheme.typography.bodyMedium
                    )}
                )
                ListItem(
                    headlineContent = { Text(
                        text = "nosotros",
                        style = MaterialTheme.typography.bodySmall
                    )},
                    trailingContent = {Text(
                        text = verbAndTense.nosotros,
                        style = MaterialTheme.typography.bodyMedium
                    )}
                )
                ListItem(
                    headlineContent = { Text(
                        text = "vosotros",
                        style = MaterialTheme.typography.bodySmall
                    )},
                    trailingContent = {Text(
                        text = verbAndTense.vosotros,
                        style = MaterialTheme.typography.bodyMedium
                    )}
                )
                ListItem(
                    headlineContent = { Text(
                        text = "ellos/ellas/ustedes",
                        style = MaterialTheme.typography.bodySmall
                    )},
                    trailingContent = {Text(
                        text = verbAndTense.ellos,
                        style = MaterialTheme.typography.bodyMedium
                    )}
                )
                Divider()
            }
        }
    }
}