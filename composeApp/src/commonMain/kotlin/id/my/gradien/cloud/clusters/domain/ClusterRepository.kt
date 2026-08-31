package id.my.gradien.cloud.clusters.domain

import id.my.gradien.cloud.core.network.utils.DataError
import id.my.gradien.cloud.core.network.utils.Result
import id.my.gradien.cloud.clusters.domain.models.Cluster

interface ClusterRepository {
    suspend fun getClusterDetails(
        email: String,
        password: String,
        id: String
    ): Result<Cluster, DataError.Remote>
}
