package id.my.gradien.cloud.nodes.core.domain

import id.my.gradien.cloud.core.network.utils.DataError
import id.my.gradien.cloud.core.network.utils.Result
import id.my.gradien.cloud.nodes.core.domain.models.Node
import id.my.gradien.cloud.nodes.core.domain.models.NodeLog
import id.my.gradien.cloud.nodes.core.domain.models.SensorData

interface NodeRepository {
    suspend fun getNodeDetails(
        email: String,
        password: String,
        id: String
    ): Result<Node, DataError.Remote>

    suspend fun getNodeLogs(
        id: String
    ): Result<NodeLog, DataError.Remote>

    suspend fun getSensorData(
        id: String,
        key: String,
        limit: Int? = null,
        start: String? = null,
        end: String? = null
    ): Result<List<SensorData>, DataError.Remote>
}
