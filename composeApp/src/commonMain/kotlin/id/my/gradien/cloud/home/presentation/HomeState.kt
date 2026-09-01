package id.my.gradien.cloud.home.presentation

import id.my.gradien.cloud.nodes.domain.models.Node
import id.my.gradien.cloud.nodes.domain.models.NodeIssue
import id.my.gradien.cloud.nodes.domain.models.SensorData

data class HomeState(
    val name: String? = null,
    val email: String? = null,
    val alerts: List<NodeIssue> = emptyList(),
    val isLoadingAlerts: Boolean = false,
    val primaryNode: Node? = null,
    val latestSensorData: SensorData? = null,
    val isLoadingNodeData: Boolean = false
)
