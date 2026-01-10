package id.my.gradien.cloud.login.domain

import id.my.gradien.cloud.core.network.utils.DataError
import id.my.gradien.cloud.core.network.utils.Result
import id.my.gradien.cloud.login.domain.models.User

interface LoginRepository {

    suspend fun login(email: String, password: String): Result<User, DataError.Remote>

}