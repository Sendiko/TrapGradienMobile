package id.my.gradien.cloud.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.gradien.cloud.core.session.SessionManager
import id.my.gradien.cloud.login.domain.LoginRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class LoginViewModel(
    private val repository: LoginRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged -> changeEmail(event.email)
            is LoginEvent.OnPasswordChanged -> changePassword(event.password)
            is LoginEvent.OnPasswordVisibleChanged -> changePasswordVisibility(event.isVisible)
            LoginEvent.ClearState -> clearState()
            LoginEvent.OnLoginClicked -> login()
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            /* commented due to API error. */
            /* repository.login(
                email = state.value.email,
                password = state.value.password
            )
                .onSuccess { user ->
                    sessionManager.saveSession(
                        name = user.name,
                        email = user.email,
                        password = user.password,
                        nodeIds = user.nodeIds,
                        clusterIds = user.clusterIds
                    )
                    _state.update { it.copy(isLoading = false, isSuccess = true) }
                }
                .onError { error ->
                    _state.update { it.copy(isError = true, isLoading = false, message = error.asUiText()) }
                }
             */
            delay(2.seconds)
            _state.update { it.copy(isLoading = false, isSuccess = true) }
        }
    }

    private fun clearState() {
        _state.value = LoginState()
    }

    private fun changePasswordVisibility(visible: Boolean) {
        _state.update { it.copy(passwordVisible = visible) }
    }

    private fun changePassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    private fun changeEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

}