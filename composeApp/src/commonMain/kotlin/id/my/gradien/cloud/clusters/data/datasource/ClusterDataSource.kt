package id.my.gradien.cloud.clusters.data.datasource

import id.my.gradien.cloud.core.network.utils.DataError
import id.my.gradien.cloud.core.network.utils.Result
import id.my.gradien.cloud.clusters.data.dto.ClusterRequest
import id.my.gradien.cloud.clusters.data.dto.ClusterResponse

interface ClusterDataSource {
    suspend fun getClusterDetails(
        request: ClusterRequest
    ): Result<ClusterResponse, DataError.Remote>
}
