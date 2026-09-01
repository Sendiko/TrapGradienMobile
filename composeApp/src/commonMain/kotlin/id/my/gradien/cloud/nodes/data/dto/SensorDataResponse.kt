package id.my.gradien.cloud.nodes.data.dto

import id.my.gradien.cloud.nodes.domain.models.SensorData
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

fun JsonObject.toSensorDataDomain(): SensorData {
    val id = this["id"]?.jsonPrimitive?.content ?: ""
    val createdAt = this["created_at"]?.jsonPrimitive?.content ?: ""
    
    val fields = this.filterKeys { it != "id" && it != "created_at" }
        .mapValues { it.value.jsonPrimitive.content }
        
    return SensorData(
        id = id,
        createdAt = createdAt,
        fields = fields
    )
}
