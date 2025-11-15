package com.amar.navigation3bottombar.ui.destinations

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeDestination : NavKey {
      @Serializable
      object Root : HomeDestination

      @Serializable
      data class Detail(val id: Int) : HomeDestination
      @Serializable
      object SubDetail : HomeDestination
}