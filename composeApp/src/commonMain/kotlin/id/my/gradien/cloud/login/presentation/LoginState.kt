package id.my.gradien.cloud.login.presentation

import id.my.gradien.cloud.core.ui.utils.UiText

data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val message: UiText = UiText.DynamicString(""),
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false
)
