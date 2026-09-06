package id.my.gradien.cloud.nodes.detail.presentation

enum class TimeRange(val label: String) {
    H1("1H"),
    H6("6H"),
    D1("1D"),
    W1("1W")
}

data class NodeDetailState(
    val nodeId: String = "",
    val nodeKey: String = "",
    val nodeName: String = "Node A-74 Detail",
    val location: String = "Living Room Unit",
    val isOnline: Boolean = true,
    val statusBadge: String = "Ion Added",
    val currentPm25: Float = 12.4f,
    val selectedTimeRange: TimeRange = TimeRange.D1,
    val sensorHistory: List<Pair<String, Float>> = defaultChartData,
    val isPowerOn: Boolean = true,
    val isIonizerOn: Boolean = true,
    val ionizerMode: String = "Boost",
    val fanSpeed: Int = 3,
    val isLoading: Boolean = false,
    val error: String? = null
) {
    companion object {
        val defaultChartData = listOf(
            "12 AM" to 15.2f,
            "3 AM" to 12.0f,
            "6 AM" to 8.5f,
            "9 AM" to 18.0f,
            "12 PM" to 14.5f,
            "3 PM" to 22.1f,
            "6 PM" to 16.3f,
            "Now" to 12.4f
        )
    }
}
