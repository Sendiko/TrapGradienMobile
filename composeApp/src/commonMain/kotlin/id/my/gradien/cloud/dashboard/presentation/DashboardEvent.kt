package id.my.gradien.cloud.dashboard.presentation

sealed interface DashboardEvent {
    data class OnLocationChanged(val newLocation: String) : DashboardEvent
    data object OnSwapLocationClicked : DashboardEvent
    data object OnDismissBottomSheet : DashboardEvent
    data object OnLihatSemuaAlatClicked : DashboardEvent
}
