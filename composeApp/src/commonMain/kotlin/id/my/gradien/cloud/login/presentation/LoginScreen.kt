package id.my.gradien.cloud.login.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(
    state: LoginState = LoginState(),
    onEvent: (LoginEvent) -> Unit,
    onNavigate: () -> Unit
) {

    Scaffold {

    }

}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}