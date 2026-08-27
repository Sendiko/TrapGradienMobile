package id.my.gradien.cloud.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.my.gradien.cloud.core.navigation.DashboardScreen
import id.my.gradien.cloud.core.navigation.LoginScreen
import id.my.gradien.cloud.core.navigation.NodeListScreen as NodeListDestination
import id.my.gradien.cloud.core.navigation.SplashScreen
import id.my.gradien.cloud.core.ui.theme.AppTheme
import id.my.gradien.cloud.dashboard.presentation.DashboardScreen
import id.my.gradien.cloud.dashboard.presentation.DashboardViewModel
import id.my.gradien.cloud.login.presentation.LoginScreen
import id.my.gradien.cloud.login.presentation.LoginViewModel
import id.my.gradien.cloud.node.list.presentation.NodeListScreen
import id.my.gradien.cloud.node.list.presentation.NodeListViewModel
import id.my.gradien.cloud.splash.presentation.SplashScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(darkTheme: Boolean) {
    AppTheme(
        darkTheme = darkTheme
    ) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = SplashScreen
        ) {
            composable<SplashScreen> {
                SplashScreen(
                    onNavigate = {
                        navController.navigate(LoginScreen) {
                            popUpTo(SplashScreen) { inclusive = true }
                        }
                    }
                )
            }
            composable<LoginScreen> {
                val viewModel = koinViewModel<LoginViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                LoginScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = {
                        navController.navigate(DashboardScreen) {
                            popUpTo(LoginScreen) { inclusive = true }
                        }
                    }
                )
            }
            composable<DashboardScreen> {
                val viewModel = koinViewModel<DashboardViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                DashboardScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigateToListAlat = {
                        navController.navigate(NodeListDestination)
                    }
                )
            }
            composable<NodeListDestination> {
                val viewModel = koinViewModel<NodeListViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                NodeListScreen(
                    state = state,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
