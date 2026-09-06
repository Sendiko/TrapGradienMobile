package id.my.gradien.cloud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import id.my.gradien.cloud.core.App
import id.my.gradien.cloud.core.ui.theme.isSystemInDarkTheme
import id.my.gradien.cloud.core.di.sharedModules
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(
                darkTheme = isSystemInDarkTheme()
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    KoinApplication(application = {
        modules(sharedModules)
    }) {
        App(
            darkTheme = isSystemInDarkTheme()
        )
    }
}
