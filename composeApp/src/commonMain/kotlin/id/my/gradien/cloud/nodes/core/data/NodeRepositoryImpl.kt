package id.my.gradien.cloud.nodes.core.data

import id.my.gradien.cloud.core.network.utils.DataError
import id.my.gradien.cloud.core.network.utils.Result
import id.my.gradien.cloud.nodes.core.data.datasource.NodeDataSource
import id.my.gradien.cloud.nodes.core.data.dto.NodeRequest
import id.my.gradien.cloud.nodes.core.data.dto.toSensorDataDomain
import id.my.gradien.cloud.nodes.core.domain.NodeRepository
import id.my.gradien.cloud.nodes.core.domain.models.Node
import id.my.gradien.cloud.nodes.core.domain.models.NodeLog
import id.my.gradien.cloud.nodes.core.domain.models.SensorData

class NodeRepositoryImpl(
    private val dataSource: NodeDataSource
) : NodeRepository {
    override suspend fun getNodeDetails(
        email: String,
        password: String,
        id: String
    ): Result<Node, DataError.Remote> {
        val request = NodeRequest(email, password, id)
        return when (val result = dataSource.getNodeDetails(request)) {
            is Result.Success -> Result.Success(result.data.toDomain())
            is Result.Error -> Result.Error(result.error)
        }
    }

    override suspend fun getNodeLogs(
        id: String
    ): Result<NodeLog, DataError.Remote> {
        return when (val result = dataSource.getNodeLogs(id)) {
            is Result.Success -> Result.Success(result.data.toDomain())
            is Result.Error -> Result.Error(result.error)
        }
    }

    override suspend fun getSensorData(
        id: String,
        key: String,
        limit: Int?,
        start: String?,
        end: String?
    ): Result<List<SensorData>, DataError.Remote> {
        return when (val result = dataSource.getSensorData(id, key, limit, start, end)) {
            is Result.Success -> Result.Success(result.data.map { it.toSensorDataDomain() })
            is Result.Error -> Result.Error(result.error)
        }
    }
}
