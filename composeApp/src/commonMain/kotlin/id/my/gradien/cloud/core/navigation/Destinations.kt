package id.my.gradien.cloud.core.navigation

import kotlinx.serialization.Serializable

@Serializable
data object SplashScreen

@Serializable
data object LoginScreen

@Serializable
data object HomeScreen

@Serializable
data class NodeScreen(val id: String, val key: String)

@Serializable
data object NodeListScreen