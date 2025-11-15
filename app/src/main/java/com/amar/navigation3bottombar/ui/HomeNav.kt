package com.amar.navigation3bottombar.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.amar.navigation3bottombar.ui.destinations.HomeDestination

@Composable
fun HomeNav(
      modifier: Modifier,
      homeBackStack: NavBackStack<NavKey>
) {
      NavDisplay(
            backStack = homeBackStack,
            onBack = { homeBackStack.removeLastOrNull() },
            entryProvider = entryProvider {
                  entry<HomeDestination.Root> {
                        HomeScreen(
                              modifier = modifier,
                              onOpenDetail = { id ->
                                    homeBackStack.add(HomeDestination.Detail(id))
                              }
                        )
                  }

                  entry<HomeDestination.Detail> { key ->
                        DetailScreen(
                              id = key.id,
                              onButtonClick = {
                                    homeBackStack.add(HomeDestination.SubDetail)
                              })
                  }

                  entry<HomeDestination.SubDetail> {
                        MoreDetailScreen()
                  }
            },
            entryDecorators = listOf(
                  rememberSaveableStateHolderNavEntryDecorator()
            )
      )
}

@Composable
fun HomeScreen(
      modifier: Modifier,
      onOpenDetail: (Int) -> Unit
) {
      Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
      ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                  Text(text = "🏠 Home")
                  Spacer(modifier = Modifier.height(12.dp))
                  Button(onClick = { onOpenDetail(123) }) {
                        Text("Open Detail 123")
                  }

                  LazyColumn {
                        items(100) { index ->
                              Text(
                                    text = "hello $index",
                                    modifier = Modifier.padding(16.dp)
                              )
                        }
                  }
            }
      }
}

@Composable
fun DetailScreen(
      id: Int,
      onButtonClick: () -> Unit
) {
      Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
      ) {
            Column {
                  Text(text = "📄 Home detail with id = $id")
                  Spacer(modifier = Modifier.height(16.dp))
                  Button(onClick = onButtonClick) {
                        Text(text = "More detail screen")
                  }
                  LazyColumn {
                        items(50) { item ->
                              Text(
                                    text = "Item number is $item",
                                    modifier = Modifier.padding(16.dp)
                              )
                        }
                  }
            }
      }
}

@Composable
fun MoreDetailScreen(modifier: Modifier = Modifier) {
      Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
      ) {
            Text(text = "More detail Screen")
      }
}