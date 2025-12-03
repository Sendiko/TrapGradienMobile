package id.my.gradien.cloud.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

@Composable
actual fun AppTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable (() -> Unit)
) {
    val colorScheme = if (darkTheme)
        darkScheme
    else lightScheme

    CompositionLocalProvider(LocalIsDarkTheme provides darkTheme) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography()
        ) {
            content()
        }
    }
}

@Composable
actual fun isSystemInDarkTheme(): Boolean {
    return UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
            UIUserInterfaceStyle.UIUserInterfaceStyleDark
}