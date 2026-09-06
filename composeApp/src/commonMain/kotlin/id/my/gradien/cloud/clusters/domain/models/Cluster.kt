package id.my.gradien.cloud.clusters.domain.models

data class Cluster(
    val id: String,
    val clusterId: String,
    val clusterKey: String,
    val name: String,
    val type: String,
    val longitude: String,
    val latitude: String,
    val description: String,
    val icon: String,
    val nodes: List<String>,
    val createdAt: String,
    val updatedAt: String
)
