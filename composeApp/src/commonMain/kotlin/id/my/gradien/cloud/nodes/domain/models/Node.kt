package id.my.gradien.cloud.nodes.domain.models

data class Node(
    val id: String,
    val nodeId: String,
    val nodeKey: String,
    val name: String,
    val type: String,
    val icon: String,
    val config: Map<String, NodeConfig>
)

data class NodeConfig(
    val icon: String,
    val scale: Scale,
    val title: String,
    val xaxis: String,
    val yaxis: String,
    val thresholds: List<Threshold>
)

data class Scale(
    val min: String,
    val max: String
)

data class Threshold(
    val from: String,
    val to: String,
    val icon: String,
    val color: String,
    val label: String
)
