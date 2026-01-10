package id.my.gradien.cloud.login.data.datasource

import id.my.gradien.cloud.core.network.utils.DataError
import id.my.gradien.cloud.core.network.utils.Result
import id.my.gradien.cloud.core.network.utils.safeCall
import id.my.gradien.cloud.login.data.dto.LoginRequest
import id.my.gradien.cloud.login.data.dto.LoginResponse
import io.ktor.client.*
import io.ktor.client.request.*

class LoginDataSourceImpl(
    val client: HttpClient
) : LoginDataSource {
    override suspend fun login(
        request: LoginRequest
    ): Result<LoginResponse, DataError.Remote> {
        return safeCall<LoginResponse> {
            client.post {
                setBody(request)
            }
        }
    }
}