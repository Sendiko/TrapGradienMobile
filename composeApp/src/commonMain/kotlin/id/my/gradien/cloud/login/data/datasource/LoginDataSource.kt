package id.my.gradien.cloud.login.data.datasource

import id.my.gradien.cloud.core.network.utils.DataError
import id.my.gradien.cloud.core.network.utils.Result
import id.my.gradien.cloud.login.data.dto.LoginRequest
import id.my.gradien.cloud.login.data.dto.LoginResponse

interface LoginDataSource {

    suspend fun login(request: LoginRequest): Result<LoginResponse, DataError.Remote>

}