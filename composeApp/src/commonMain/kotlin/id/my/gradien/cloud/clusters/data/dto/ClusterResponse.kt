package id.my.gradien.cloud.clusters.data.dto

import id.my.gradien.cloud.clusters.domain.models.Cluster
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ClusterResponse(
    @SerialName("id")
    val id: String? = null,
    @SerialName("cluster_id")
    val clusterId: String? = null,
    @SerialName("cluster_key")
    val clusterKey: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("longitude")
    val longitude: String? = null,
    @SerialName("latitude")
    val latitude: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("icon")
    val icon: String? = null,
    @SerialName("nodes")
    val nodes: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null
) {
    fun toDomain(): Cluster {
        val parsedNodes = try {
            nodes?.let { Json.decodeFromString<List<String>>(it) } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
        
        return Cluster(
            id = id ?: "",
            clusterId = clusterId ?: "",
            clusterKey = clusterKey ?: "",
            name = name ?: "",
            type = type ?: "",
            longitude = longitude ?: "0",
            latitude = latitude ?: "0",
            description = description ?: "",
            icon = icon ?: "",
            nodes = parsedNodes,
            createdAt = createdAt ?: "",
            updatedAt = updatedAt ?: ""
        )
    }
}
