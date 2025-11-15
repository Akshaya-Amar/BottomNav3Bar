package com.amar.navigation3bottombar.ui.destinations

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface OrderDestination : NavKey {
      @Serializable
      object Root : OrderDestination
}