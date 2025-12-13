package id.my.gradien.cloud

import androidx.compose.ui.window.ComposeUIViewController
import id.my.gradien.cloud.core.App
import id.my.gradien.cloud.core.di.initKoin
import id.my.gradien.cloud.core.ui.theme.isSystemInDarkTheme

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    App(darkTheme = isSystemInDarkTheme())
}