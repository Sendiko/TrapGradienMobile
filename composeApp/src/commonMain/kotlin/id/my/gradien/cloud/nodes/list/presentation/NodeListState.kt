package id.my.gradien.cloud.nodes.list.presentation

import id.my.gradien.cloud.nodes.list.presentation.models.NodeFilter
import id.my.gradien.cloud.nodes.list.presentation.models.NodeItemUi
import id.my.gradien.cloud.nodes.list.presentation.models.NodeStatusUi

data class NodeListState(
    val searchQuery: String = "",
    val selectedFilter: NodeFilter = NodeFilter.ALL,
    val nodes: List<NodeItemUi> = defaultNodes,
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val filteredNodes: List<NodeItemUi>
        get() {
            return nodes.filter { node ->
                val matchesFilter = when (selectedFilter) {
                    NodeFilter.ALL -> true
                    NodeFilter.ONLINE -> node.status == NodeStatusUi.ONLINE
                    NodeFilter.OFFLINE -> node.status == NodeStatusUi.OFFLINE
                    NodeFilter.ALERTS -> node.status == NodeStatusUi.ALERT
                }
                val matchesQuery = if (searchQuery.isBlank()) {
                    true
                } else {
                    node.name.contains(searchQuery, ignoreCase = true) ||
                            node.nodeId.contains(searchQuery, ignoreCase = true) ||
                            node.location.contains(searchQuery, ignoreCase = true)
                }
                matchesFilter && matchesQuery
            }
        }

    companion object {
        val defaultNodes = listOf(
            NodeItemUi(
                id = "1",
                nodeId = "node-a4",
                name = "Node-A4",
                location = "Living Room",
                status = NodeStatusUi.ONLINE,
                aqi = 42,
                aqiLabel = "Good",
                batteryPercent = 85
            ),
            NodeItemUi(
                id = "2",
                nodeId = "node-b2",
                name = "Node-B2",
                location = "Basement",
                status = NodeStatusUi.OFFLINE,
                aqi = null,
                aqiLabel = null,
                batteryPercent = 0
            ),
            NodeItemUi(
                id = "3",
                nodeId = "node-k9",
                name = "Node-K9",
                location = "Kitchen",
                status = NodeStatusUi.ALERT,
                aqi = 115,
                aqiLabel = "Poor",
                batteryPercent = 42
            ),
            NodeItemUi(
                id = "4",
                nodeId = "node-m1",
                name = "Node-M1",
                location = "Master Bedroom",
                status = NodeStatusUi.ONLINE,
                aqi = 28,
                aqiLabel = "Good",
                batteryPercent = 92
            ),
            NodeItemUi(
                id = "5",
                nodeId = "node-o3",
                name = "Node-O3",
                location = "Outdoor Patio",
                status = NodeStatusUi.ONLINE,
                aqi = 65,
                aqiLabel = "Moderate",
                batteryPercent = 68
            )
        )
    }
}
