package id.my.gradien.cloud.clusters.data.datasource

import id.my.gradien.cloud.core.network.utils.DataError
import id.my.gradien.cloud.core.network.utils.Result
import id.my.gradien.cloud.core.network.utils.safeCall
import id.my.gradien.cloud.clusters.data.dto.ClusterRequest
import id.my.gradien.cloud.clusters.data.dto.ClusterResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.FormDataContent
import io.ktor.http.Parameters

class ClusterDataSourceImpl(
    private val client: HttpClient
) : ClusterDataSource {
    override suspend fun getClusterDetails(
        request: ClusterRequest
    ): Result<ClusterResponse, DataError.Remote> {
        return safeCall {
            client.post("cluster") {
                setBody(
                    FormDataContent(
                        Parameters.build {
                            append("email", request.email)
                            append("password", request.password)
                            append("id", request.id)
                        }
                    )
                )
            }
        }
    }
}
