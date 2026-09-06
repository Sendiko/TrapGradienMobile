package id.my.gradien.cloud.clusters.data

import id.my.gradien.cloud.core.network.utils.DataError
import id.my.gradien.cloud.core.network.utils.Result
import id.my.gradien.cloud.clusters.data.datasource.ClusterDataSource
import id.my.gradien.cloud.clusters.data.dto.ClusterRequest
import id.my.gradien.cloud.clusters.domain.ClusterRepository
import id.my.gradien.cloud.clusters.domain.models.Cluster

class ClusterRepositoryImpl(
    private val dataSource: ClusterDataSource
) : ClusterRepository {
    override suspend fun getClusterDetails(
        email: String,
        password: String,
        id: String
    ): Result<Cluster, DataError.Remote> {
        val request = ClusterRequest(email, password, id)
        return when (val result = dataSource.getClusterDetails(request)) {
            is Result.Success -> Result.Success(result.data.toDomain())
            is Result.Error -> Result.Error(result.error)
        }
    }
}
