package id.my.gradien.cloud.nodes.data.datasource

import id.my.gradien.cloud.core.network.utils.DataError
import id.my.gradien.cloud.core.network.utils.Result
import id.my.gradien.cloud.core.network.utils.safeCall
import id.my.gradien.cloud.nodes.data.dto.NodeLogResponse
import id.my.gradien.cloud.nodes.data.dto.NodeRequest
import id.my.gradien.cloud.nodes.data.dto.NodeResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.json.JsonObject

class NodeDataSourceImpl(
    private val client: HttpClient
) : NodeDataSource {
    override suspend fun getNodeDetails(
        request: NodeRequest
    ): Result<NodeResponse, DataError.Remote> {
        return safeCall {
            client.post("node") {
                setBody(request)
            }
        }
    }

    override suspend fun getNodeLogs(
        id: String
    ): Result<NodeLogResponse, DataError.Remote> {
        return safeCall {
            client.get("logs") {
                parameter("id", id)
            }
        }
    }

    override suspend fun getSensorData(
        id: String,
        key: String,
        limit: Int?,
        start: String?,
        end: String?
    ): Result<List<JsonObject>, DataError.Remote> {
        return safeCall {
            client.get("read") {
                parameter("id", id)
                parameter("key", key)
                limit?.let { parameter("limit", it) }
                start?.let { parameter("start", it) }
                end?.let { parameter("end", it) }
            }
        }
    }
}
