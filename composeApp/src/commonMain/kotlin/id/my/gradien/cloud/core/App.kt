package id.my.gradien.cloud.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import id.my.gradien.cloud.core.navigation.ClustersScreen
import id.my.gradien.cloud.core.navigation.HomeScreen
import id.my.gradien.cloud.core.navigation.LoginScreen
import id.my.gradien.cloud.core.navigation.Navigator
import id.my.gradien.cloud.core.navigation.NodeListScreen
import id.my.gradien.cloud.core.navigation.NodeScreen
import id.my.gradien.cloud.core.navigation.ProfileScreen
import id.my.gradien.cloud.core.navigation.Route
import id.my.gradien.cloud.core.navigation.SplashScreen
import id.my.gradien.cloud.core.navigation.rememberNavigationState
import id.my.gradien.cloud.core.navigation.toEntries
import id.my.gradien.cloud.core.ui.theme.AppTheme
import id.my.gradien.cloud.login.presentation.LoginScreen
import id.my.gradien.cloud.login.presentation.LoginViewModel
import id.my.gradien.cloud.splash.presentation.SplashScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(darkTheme: Boolean = false) {
    AppTheme(
        darkTheme = darkTheme
    ) {
        val config = remember {
            SavedStateConfiguration {
                serializersModule = SerializersModule {
                    polymorphic(NavKey::class) {
                        subclass(SplashScreen::class, SplashScreen.serializer())
                        subclass(LoginScreen::class, LoginScreen.serializer())
                        subclass(HomeScreen::class, HomeScreen.serializer())
                        subclass(ClustersScreen::class, ClustersScreen.serializer())
                        subclass(NodeListScreen::class, NodeListScreen.serializer())
                        subclass(NodeScreen::class, NodeScreen.serializer())
                        subclass(ProfileScreen::class, ProfileScreen.serializer())
                    }
                }
            }
        }

        val mainTabs = setOf(HomeScreen, ClustersScreen, NodeListScreen, ProfileScreen)
        val allTopLevelRoutes = setOf(SplashScreen, LoginScreen) + mainTabs

        val navigationState = rememberNavigationState(
            configuration = config,
            startRoute = SplashScreen,
            topLevelRoutes = allTopLevelRoutes
        )
        val navigator = remember { Navigator(navigationState) }

        val entryProvider = entryProvider<Route> {
            entry<SplashScreen> {
                SplashScreen(
                    onNavigate = { navigator.navigate(LoginScreen) }
                )
            }
            entry<LoginScreen> {
                val viewModel = koinViewModel<LoginViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                LoginScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = { navigator.navigate(HomeScreen) }
                )
            }
            entry<HomeScreen> {
                Surface { Text("Home Screen Content") }
            }
            entry<ClustersScreen> {
                Surface { Text("Clusters Screen Content") }
            }
            entry<NodeListScreen> {
                Surface { Text("Nodes Screen Content") }
            }
            entry<ProfileScreen> {
                Surface { Text("Profile Screen Content") }
            }
        }

        Scaffold(
            bottomBar = {
                if (navigationState.topLevelRoute in mainTabs) {
                    NavigationBar {
                        NavigationBarItem(
                            selected = navigationState.topLevelRoute == HomeScreen,
                            onClick = { navigator.navigate(HomeScreen) },
                            icon = { Icon(Icons.Default.Dashboard, contentDescription = "Home") },
                            label = { Text("Home") }
                        )
                        NavigationBarItem(
                            selected = navigationState.topLevelRoute == ClustersScreen,
                            onClick = { navigator.navigate(ClustersScreen) },
                            icon = { Icon(Icons.Default.Hub, contentDescription = "Clusters") },
                            label = { Text("Clusters") }
                        )
                        NavigationBarItem(
                            selected = navigationState.topLevelRoute == NodeListScreen,
                            onClick = { navigator.navigate(NodeListScreen) },
                            icon = { Icon(Icons.Default.Sensors, contentDescription = "Nodes") },
                            label = { Text("Nodes") }
                        )
                        NavigationBarItem(
                            selected = navigationState.topLevelRoute == ProfileScreen,
                            onClick = { navigator.navigate(ProfileScreen) },
                            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Profile") },
                            label = { Text("Profile") }
                        )
                    }
                }
            }
        ) { paddingValues ->
            NavDisplay(
                modifier = Modifier.padding(paddingValues),
                entries = navigationState.toEntries(entryProvider),
                onBack = { navigator.goBack() }
            )
        }
    }
}
