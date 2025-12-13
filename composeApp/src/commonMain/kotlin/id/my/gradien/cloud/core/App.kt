package id.my.gradien.cloud.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.my.gradien.cloud.core.navigation.LoginScreen
import id.my.gradien.cloud.core.navigation.SplashScreen
import id.my.gradien.cloud.core.ui.theme.AppTheme
import id.my.gradien.cloud.splash.presentation.SplashScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

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
                SplashScreen()
            }
            composable<LoginScreen> {

            }
        }
    }
}