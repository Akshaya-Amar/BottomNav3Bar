package com.amar.navigation3bottombar.ui.destinations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Tab(
      val label: String,
      val icon: ImageVector
) {
      data object Home : Tab("Home", Icons.Default.Home)
      data object Search : Tab("Search", Icons.Default.Search)
      data object Profile : Tab("Profile", Icons.Default.Person)
}


/*
sealed interface Tab {
      val label: String
      val icon: ImageVector


      object Home : Tab {
            override val label: String
                  get() = "Home"

            override val icon: ImageVector
                  get() = Icons.Default.Home

      }


      object Search : Tab {
            override val label: String
                  get() = "Search"

            override val icon: ImageVector
                  get() = Icons.Default.Search
      }


      object Profile : Tab {
            override val label: String
                  get() = "Profile"

            override val icon: ImageVector
                  get() = Icons.Default.Person
      }
}*/
