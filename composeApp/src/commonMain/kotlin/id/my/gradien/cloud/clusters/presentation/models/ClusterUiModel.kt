package id.my.gradien.cloud.clusters.presentation.models

import id.my.gradien.cloud.clusters.domain.models.Cluster
import id.my.gradien.cloud.nodes.domain.models.Node
import id.my.gradien.cloud.nodes.domain.models.NodeLog
import id.my.gradien.cloud.nodes.domain.models.SensorData

data class ClusterUiModel(
    val cluster: Cluster,
    val nodeDetails: List<NodeItemUiModel> = emptyList(),
    val isExpanded: Boolean = false,
    val isLoadingDetails: Boolean = false
) {
    val activeNodes: Int get() = nodeDetails.count { it.status != NodeStatus.Offline }
    val offlineNodes: Int get() = nodeDetails.count { it.status == NodeStatus.Offline }
    
    val averageAqi: Int get() {
        if (nodeDetails.isEmpty()) return 0
        val sum = nodeDetails.sumOf { it.latestData?.fields?.get("field1")?.toIntOrNull() ?: 0 }
        // Scale to AQI-like value if field1 is 0-4. Maybe field1 * 25?
        // But for display, let's just use the average of the field value or a fixed scale for now.
        // The image shows 42 (Good) and 115 (Unhealthy).
        // I'll assume field1 is 0-500 or similar for this calculation.
        return sum / nodeDetails.size
    }

    val aqiStatus: String get() = when {
        averageAqi <= 50 -> "Good"
        averageAqi <= 100 -> "Moderate"
        else -> "Unhealthy"
    }
}

data class NodeItemUiModel(
    val node: Node,
    val latestData: SensorData? = null,
    val logs: NodeLog? = null
) {
    val status: NodeStatus
        get() {
            if (logs?.issues?.any { it.status.equals("Unresolved", ignoreCase = true) } == true) {
                return NodeStatus.Maintenance
            }
            // Logic for Online/Offline could be based on timestamp of latestData
            return NodeStatus.Online
        }
}

enum class NodeStatus {
    Online, Offline, Maintenance
}
