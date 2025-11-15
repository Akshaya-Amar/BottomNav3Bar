package com.amar.navigation3bottombar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.rememberNavBackStack
import com.amar.navigation3bottombar.ui.HomeNav
import com.amar.navigation3bottombar.ui.ProfileNav
import com.amar.navigation3bottombar.ui.SearchNav
import com.amar.navigation3bottombar.ui.destinations.HomeDestination
import com.amar.navigation3bottombar.ui.destinations.ProfileDestination
import com.amar.navigation3bottombar.ui.destinations.SearchDestination
import com.amar.navigation3bottombar.ui.destinations.Tab
import com.amar.navigation3bottombar.ui.theme.Navigation3BottomBarTheme

class MainActivity : ComponentActivity() {
      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                  Navigation3BottomBarTheme {
                        AppComposable()
                  }
            }
      }
}

@Composable
fun AppComposable() {
      // Each top-level tab maintains its own NavBackStack
      val homeStack = rememberNavBackStack(HomeDestination.Root)
      val searchStack = rememberNavBackStack(SearchDestination.Root)
      val profileStack = rememberNavBackStack(ProfileDestination.Root)

      // SaveableStateHolders for restoring scroll positions etc.
      val homeStateHolder = rememberSaveableStateHolder()
      val searchStateHolder = rememberSaveableStateHolder()
      val profileStateHolder = rememberSaveableStateHolder()

      var currentTab by remember { mutableStateOf<Tab>(Tab.Home) }
      val currentActivity = LocalActivity.current

      BackHandler {
            val currentStack = when (currentTab) {
                  Tab.Home -> homeStack
                  Tab.Search -> searchStack
                  Tab.Profile -> profileStack
            }

            if (currentStack.size > 1) {
                  currentStack.removeLastOrNull()
            } else {
                  if (currentTab != Tab.Home) {
                        currentTab = Tab.Home
                  } else {
                        currentActivity?.finish()
                  }
            }
      }

      val isBottomBarVisible = when (currentTab) {
            Tab.Home -> homeStack.lastOrNull() is HomeDestination.Root
            Tab.Search -> searchStack.lastOrNull() is SearchDestination.Root
            Tab.Profile -> profileStack.lastOrNull() is ProfileDestination.Root
      }

      val tabs = listOf(Tab.Home, Tab.Search, Tab.Profile)

      Scaffold(
            bottomBar = {
                  if (isBottomBarVisible) {
                        NavigationBar {
                              tabs.forEach { tab ->
                                    NavigationBarItem(
                                          selected = currentTab == tab,
                                          onClick = { currentTab = tab },
                                          label = { Text(tab.label) },
                                          icon = {
                                                Icon(
                                                      imageVector = tab.icon,
                                                      contentDescription = tab.label
                                                )
                                          }
                                    )
                              }
                        }
                  }
            }
      ) { innerPadding ->
            when (currentTab) {
                  Tab.Home -> homeStateHolder.SaveableStateProvider(Tab.Home.label) {
                        HomeNav(
                              modifier = Modifier.padding(innerPadding),
                              homeBackStack = homeStack
                        )
                  }

                  Tab.Search -> searchStateHolder.SaveableStateProvider(Tab.Search.label) {
                        SearchNav(searchStack)
                  }

                  Tab.Profile -> profileStateHolder.SaveableStateProvider(Tab.Profile.label) {
                        ProfileNav(profileStack)
                  }
            }
      }
}