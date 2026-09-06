package id.my.gradien.cloud.login.domain.models

data class User(
    val username: String,
    val name: String,
    val email: String,
    val password: String,
    val nodeIds: List<String>,
    val clusterIds: List<String>
)