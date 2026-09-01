package id.my.gradien.cloud.nodes.data.datasource

import id.my.gradien.cloud.core.network.utils.DataError
import id.my.gradien.cloud.core.network.utils.Result
import id.my.gradien.cloud.nodes.data.dto.NodeLogResponse
import id.my.gradien.cloud.nodes.data.dto.NodeRequest
import id.my.gradien.cloud.nodes.data.dto.NodeResponse
import kotlinx.serialization.json.JsonObject

interface NodeDataSource {
    suspend fun getNodeDetails(
        request: NodeRequest
    ): Result<NodeResponse, DataError.Remote>

    suspend fun getNodeLogs(
        id: String
    ): Result<NodeLogResponse, DataError.Remote>

    suspend fun getSensorData(
        id: String,
        key: String,
        limit: Int? = null,
        start: String? = null,
        end: String? = null
    ): Result<List<JsonObject>, DataError.Remote>
}
