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
import com.amar.navigation3bottombar.ui.destinations.ProfileDestination

@Composable
fun ProfileNav(profileBackStack: NavBackStack<NavKey>) {
      NavDisplay(
            backStack = profileBackStack,
            onBack = { profileBackStack.removeLastOrNull() },
            entryProvider = entryProvider {
                  entry<ProfileDestination.Root> {
                        ProfileScreen(
                              onOpenSettings = {
                                    profileBackStack.add(ProfileDestination.Settings)
                              }
                        )
                  }

                  entry<ProfileDestination.Settings> {
                        SettingsScreen()
                  }
            },
            entryDecorators = listOf(
                  rememberSaveableStateHolderNavEntryDecorator()
            )
      )
}

@Composable
fun ProfileScreen(onOpenSettings: () -> Unit) {
      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                  Text("👤 Profile")
                  Spacer(modifier = Modifier.height(8.dp))
                  Button(onClick = { onOpenSettings() }) {
                        Text("Open Settings")
                  }
            }
      }
}

@Composable
fun SettingsScreen() {
      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("⚙️ Settings")
      }
}