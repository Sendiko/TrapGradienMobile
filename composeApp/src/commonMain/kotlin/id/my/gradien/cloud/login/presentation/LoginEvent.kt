package id.my.gradien.cloud.login.presentation

sealed interface LoginEvent {
    data class OnEmailChanged(val email: String) : LoginEvent
    data class OnPasswordChanged(val password: String) : LoginEvent
    data class OnPasswordVisibleChanged(val isVisible: Boolean) : LoginEvent
    data object ClearState : LoginEvent
    data object OnLoginClicked : LoginEvent
}