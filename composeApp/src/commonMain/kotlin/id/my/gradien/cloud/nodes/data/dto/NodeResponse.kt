package id.my.gradien.cloud.nodes.data.dto

import id.my.gradien.cloud.nodes.domain.models.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class NodeResponse(
    @SerialName("id")
    val id: String? = null,
    @SerialName("node_id")
    val nodeId: String? = null,
    @SerialName("node_key")
    val nodeKey: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("icon")
    val icon: String? = null,
    @SerialName("config")
    val config: String? = null
) {
    fun toDomain(): Node {
        val parsedConfig = try {
            config?.let { Json.decodeFromString<Map<String, ConfigDto>>(it) } ?: emptyMap()
        } catch (e: Exception) {
            emptyMap()
        }

        return Node(
            id = id ?: "",
            nodeId = nodeId ?: "",
            nodeKey = nodeKey ?: "",
            name = name ?: "",
            type = type ?: "",
            icon = icon ?: "",
            config = parsedConfig.mapValues { it.value.toDomain() }
        )
    }
}

@Serializable
data class ConfigDto(
    @SerialName("icon")
    val icon: String? = null,
    @SerialName("scale")
    val scale: ScaleDto? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("xaxis")
    val xaxis: String? = null,
    @SerialName("yaxis")
    val yaxis: String? = null,
    @SerialName("thresholds")
    val thresholds: List<ThresholdDto>? = null
) {
    fun toDomain() = NodeConfig(
        icon = icon ?: "",
        scale = scale?.toDomain() ?: Scale("0", "0"),
        title = title ?: "",
        xaxis = xaxis ?: "",
        yaxis = yaxis ?: "",
        thresholds = thresholds?.map { it.toDomain() } ?: emptyList()
    )
}

@Serializable
data class ScaleDto(
    @SerialName("min")
    val min: String? = null,
    @SerialName("max")
    val max: String? = null
) {
    fun toDomain() = Scale(min ?: "0", max ?: "0")
}

@Serializable
data class ThresholdDto(
    @SerialName("from")
    val from: String? = null,
    @SerialName("to")
    val to: String? = null,
    @SerialName("icon")
    val icon: String? = null,
    @SerialName("color")
    val color: String? = null,
    @SerialName("label")
    val label: String? = null
) {
    fun toDomain() = Threshold(
        from = from ?: "",
        to = to ?: "",
        icon = icon ?: "",
        color = color ?: "",
        label = label ?: ""
    )
}
