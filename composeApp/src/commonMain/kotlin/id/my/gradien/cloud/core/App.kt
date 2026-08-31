package id.my.gradien.cloud.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import id.my.gradien.cloud.core.navigation.HomeScreen
import id.my.gradien.cloud.core.navigation.LoginScreen
import id.my.gradien.cloud.core.navigation.NavigationState
import id.my.gradien.cloud.core.navigation.Navigator
import id.my.gradien.cloud.core.navigation.NodeListScreen
import id.my.gradien.cloud.core.navigation.NodeScreen
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
                        subclass(NodeListScreen::class, NodeListScreen.serializer())
                        subclass(NodeScreen::class, NodeScreen.serializer())
                    }
                }
            }
        }
        val navigationState = rememberNavigationState(
            configuration = config,
            startRoute = SplashScreen,
            topLevelRoutes = setOf(SplashScreen, LoginScreen)
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
                    onNavigate = { /* Navigation to Home will be here */ }
                )
            }
        }

        NavDisplay(
            entries = navigationState.toEntries(entryProvider),
            onBack = { navigator.goBack() }
        )
    }
}
