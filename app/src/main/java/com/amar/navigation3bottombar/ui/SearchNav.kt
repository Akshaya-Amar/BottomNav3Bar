package com.amar.navigation3bottombar.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.amar.navigation3bottombar.ui.destinations.SearchDestination

@Composable
fun SearchNav(searchBackStack: NavBackStack<NavKey>) {
      NavDisplay(
            backStack = searchBackStack,
            onBack = { searchBackStack.removeLastOrNull() },
            entryProvider = entryProvider {
                  entry<SearchDestination.Root> {
                        SearchScreen(
                              onSelectResult = { query ->
                                    searchBackStack.add(SearchDestination.QueryResult(query))
                              }
                        )
                  }

                  entry<SearchDestination.QueryResult> { key ->
                        QueryResultScreen(query = key.query)
                  }
            },
            entryDecorators = listOf(
                  rememberSaveableStateHolderNavEntryDecorator()
            )
      )
}

@Composable
fun SearchScreen(onSelectResult: (String) -> Unit) {
      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                  Text("🔍 Search")
                  Spacer(modifier = Modifier.height(8.dp))
                  Button(onClick = { onSelectResult("android") }) {
                        Text("Show results for 'android'")
                  }
            }
      }
}

@Composable
fun QueryResultScreen(query: String) {
      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Results for: $query")
      }
}