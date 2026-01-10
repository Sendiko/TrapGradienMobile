package id.my.gradien.cloud.login.data.dto

import id.my.gradien.cloud.login.domain.models.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(

	@SerialName("reset_token")
	val resetToken: String? = null,

	@SerialName("role")
	val role: String? = null,

	@SerialName("additional")
	val additional: String? = null,

	@SerialName("created_at")
	val createdAt: String? = null,

	@SerialName("password")
	val password: String? = null,

	@SerialName("url_foto")
	val urlFoto: String? = null,

	@SerialName("nodes")
	val nodes: String? = null,

	@SerialName("updated_at")
	val updatedAt: String? = null,

	@SerialName("name")
	val name: String? = null,

	@SerialName("id")
	val id: String? = null,

	@SerialName("email")
	val email: String? = null,

	@SerialName("clusters")
	val clusters: String? = null,

	@SerialName("username")
	val username: String? = null
) {
    fun toDomain() = User(
        username = username ?: "",
        name = name ?: "",
        email = email ?: "",
        password = password ?: ""
    )
}
