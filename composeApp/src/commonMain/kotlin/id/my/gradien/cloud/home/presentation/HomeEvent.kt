package id.my.gradien.cloud.home.presentation

sealed interface HomeEvent {
    data object OnLoadData: HomeEvent
    data object OnRefresh: HomeEvent
}