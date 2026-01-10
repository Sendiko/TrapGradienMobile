package id.my.gradien.cloud.login.data

import id.my.gradien.cloud.core.network.utils.DataError
import id.my.gradien.cloud.core.network.utils.Result
import id.my.gradien.cloud.login.data.datasource.LoginDataSource
import id.my.gradien.cloud.login.data.dto.LoginRequest
import id.my.gradien.cloud.login.domain.LoginRepository
import id.my.gradien.cloud.login.domain.models.User

class LoginRepositoryImpl(
    val dataSource: LoginDataSource
) : LoginRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<User, DataError.Remote> {
        val request = LoginRequest(email, password)
        return when(val response = dataSource.login(request)) {
            is Result.Success -> Result.Success(response.data.toDomain())
            is Result.Error -> Result.Error(response.error)
        }
    }
}