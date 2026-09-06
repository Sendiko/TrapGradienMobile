package id.my.gradien.cloud.nodes.list.presentation.models

enum class NodeStatusUi {
    ONLINE,
    OFFLINE,
    ALERT
}

enum class NodeFilter(val label: String) {
    ALL("All Nodes"),
    ONLINE("Online"),
    OFFLINE("Offline"),
    ALERTS("Alerts")
}

data class NodeItemUi(
    val id: String,
    val nodeId: String,
    val name: String,
    val location: String,
    val status: NodeStatusUi,
    val aqi: Int?,
    val aqiLabel: String?,
    val batteryPercent: Int?
)
