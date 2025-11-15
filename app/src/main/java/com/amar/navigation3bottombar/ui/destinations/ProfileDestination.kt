package com.amar.navigation3bottombar.ui.destinations

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface ProfileDestination : NavKey {
      @Serializable
      object Root : ProfileDestination
      @Serializable object Settings : ProfileDestination
}