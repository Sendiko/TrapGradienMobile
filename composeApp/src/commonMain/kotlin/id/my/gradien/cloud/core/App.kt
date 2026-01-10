package id.my.gradien.cloud.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.my.gradien.cloud.core.navigation.LoginScreen
import id.my.gradien.cloud.core.navigation.SplashScreen
import id.my.gradien.cloud.core.ui.theme.AppTheme
import id.my.gradien.cloud.login.presentation.LoginViewModel
import id.my.gradien.cloud.splash.presentation.SplashScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
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
                    onNavigate = { navController.navigate(LoginScreen) }
                )
            }
            composable<LoginScreen> {
                val viewModel = koinViewModel<LoginViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

            }
        }
    }
}