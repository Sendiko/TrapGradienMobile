package id.my.gradien.cloud.nodes.domain.models

data class SensorData(
    val id: String,
    val createdAt: String,
    val fields: Map<String, String>
)
