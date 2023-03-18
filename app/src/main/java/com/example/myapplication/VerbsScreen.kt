package com.example.myapplication

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.entities.Verb
import com.example.myapplication.entities.immutableListOfVerbs
import kotlinx.coroutines.flow.*

class VerbScreenViewModel : ViewModel(){

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _verbs = MutableStateFlow(immutableListOfVerbs)
    val verbs = searchText.combine(_verbs) { text, verbs ->
        if(text.isBlank()){
            verbs
        } else {
            verbs.filter {
                it.doesMatchSearchQuery(text)
            }
        }
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _verbs.value
        )

    fun onSearchTextChange(text: String){
        _searchText.value = text
    }

    fun onIsSearchActiveChange(boolean: Boolean){
        _isSearching.value = boolean
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerbsScreen(navController: NavController) {
    val viewModel = viewModel<VerbScreenViewModel>()
    val isSearchActive by viewModel.isSearching.collectAsState()
    val verbs by viewModel.verbs.collectAsState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { if(isSearchActive) {
                    VerbScreenSearchBar()
                } else {
                    Text("Verbs")
                }
                        },
                modifier = Modifier,
                navigationIcon = {
                    if(!isSearchActive){
                        IconButton(
                            onClick = {
                                navController.navigate("practice")
                            }
                        ) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back arrow")
                        }
                    }
                },
//                actions = {
//                    IconButton(onClick = {
//                        viewModel.onIsSearchActiveChange(true)
//                    }) {
//                        Icon(Icons.Default.Search, contentDescription = "Search")
//                    }
//                },
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
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                ) {
                    VerbScreenSearchBar()
                    VerbsForVerbsScreen(navController = navController, verbs = verbs)
                }
            }
    )
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun VerbScreenSearchBar(){
    val viewModel = viewModel<VerbScreenViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val keyboard = LocalSoftwareKeyboardController.current
    TextField(
        value = searchText,
        onValueChange = viewModel::onSearchTextChange,
        placeholder = { Text(text = "Search") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onSearch = { keyboard?.hide() }
        )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerbsForVerbsScreen(navController: NavController, verbs: List<Verb>) {
    val groupedVerbs = verbs.groupBy { it.common }
    val commonVerbs = groupedVerbs[true].orEmpty().sortedBy { it.infinitive }
    val uncommonVerbs = groupedVerbs[false].orEmpty().sortedBy { it.infinitive }

    //Empty state for search
    if (commonVerbs.isEmpty() && uncommonVerbs.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No results found that match your search. Try searching for the spanish infinitive or for the English meaning.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
        ) {
            if (commonVerbs.isNotEmpty()) {
                stickyHeader {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Common Verbs",
                            modifier = Modifier
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
                items(commonVerbs.size) { index ->
                    val verb = commonVerbs[index]
                    ListItem(
                        modifier = Modifier.noRippleClickable(
                            onClick = { navController.navigate("verb/${verb.infinitive}") }
                        ),
                        headlineText = { Text(verb.infinitive) },
                        trailingContent = {
                            val regex = Regex("[,;\\[]")
                            Row(
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(verb.englishInfinitive.split(regex).first())
                                Icon(
                                    Icons.Default.KeyboardArrowRight,
                                    contentDescription = "Right arrow"
                                )
                            }
                        }
                    )
                }
            }
            if (uncommonVerbs.isNotEmpty()) {
                stickyHeader {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Uncommon Verbs",
                            modifier = Modifier
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
                items(uncommonVerbs.size) { index ->
                    val verb = uncommonVerbs[index]
                    ListItem(
                        modifier = Modifier.noRippleClickable(
                            onClick = { navController.navigate("verb/${verb.infinitive}") }
                        ),
                        headlineText = { Text(verb.infinitive) },
                        trailingContent = {
                            val regex = Regex("[,;\\[]")
                            Row(
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(verb.englishInfinitive.split(regex).first())
                                Icon(
                                    Icons.Default.KeyboardArrowRight,
                                    contentDescription = "Right arrow"
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

inline fun Modifier.noRippleClickable(
    crossinline onClick: () -> Unit
): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}