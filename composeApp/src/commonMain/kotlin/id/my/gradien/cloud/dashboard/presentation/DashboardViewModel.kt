package id.my.gradien.cloud.dashboard.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DashboardViewModel : ViewModel() {
    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.OnLocationChanged -> {
                _state.update { 
                    it.copy(
                        location = event.newLocation,
                        isLocationPickerOpen = false
                    ) 
                }
            }
            DashboardEvent.OnSwapLocationClicked -> {
                _state.update { it.copy(isLocationPickerOpen = true) }
            }
            DashboardEvent.OnDismissBottomSheet -> {
                _state.update { it.copy(isLocationPickerOpen = false) }
            }
            DashboardEvent.OnLihatSemuaAlatClicked -> {
                // Handle navigation or action
            }
        }
    }
}
