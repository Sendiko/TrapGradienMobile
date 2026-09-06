package id.my.gradien.cloud.nodes.detail.presentation

sealed interface NodeDetailEvent {
    data class OnTimeRangeSelected(val timeRange: TimeRange) : NodeDetailEvent
    data class OnPowerToggled(val isPowerOn: Boolean) : NodeDetailEvent
    data class OnIonizerToggled(val isIonizerOn: Boolean) : NodeDetailEvent
    data class OnIonizerModeSelected(val mode: String) : NodeDetailEvent
    data class OnFanSpeedChanged(val speed: Int) : NodeDetailEvent
}
