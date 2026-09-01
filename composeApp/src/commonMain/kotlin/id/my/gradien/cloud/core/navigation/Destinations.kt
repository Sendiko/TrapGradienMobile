package id.my.gradien.cloud.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route

@Serializable
data object SplashScreen : Route()

@Serializable
data object LoginScreen : Route()

@Serializable
data object HomeScreen : Route()

@Serializable
data object ClustersScreen : Route()

@Serializable
data object NodeListScreen : Route()

@Serializable
data class NodeScreen(val id: String, val key: String) : Route()

@Serializable
data object ProfileScreen : Route()
