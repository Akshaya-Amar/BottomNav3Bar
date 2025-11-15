package com.amar.navigation3bottombar.ui.destinations

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface SearchDestination : NavKey {
      @Serializable
      object Root : SearchDestination
      @Serializable data class QueryResult(val query: String) : SearchDestination
}