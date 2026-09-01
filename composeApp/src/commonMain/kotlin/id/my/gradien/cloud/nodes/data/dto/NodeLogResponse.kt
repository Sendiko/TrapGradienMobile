package id.my.gradien.cloud.nodes.data.dto

import id.my.gradien.cloud.nodes.domain.models.NodeIssue
import id.my.gradien.cloud.nodes.domain.models.NodeLog
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NodeLogResponse(
    @SerialName("issues")
    val issues: List<IssueDto>? = null,
    @SerialName("commands")
    val commands: List<String>? = null
) {
    fun toDomain() = NodeLog(
        issues = issues?.map { it.toDomain() } ?: emptyList(),
        commands = commands ?: emptyList()
    )
}

@Serializable
data class IssueDto(
    @SerialName("time")
    val time: String? = null,
    @SerialName("issue")
    val issue: String? = null,
    @SerialName("status")
    val status: String? = null
) {
    fun toDomain() = NodeIssue(
        time = time ?: "",
        issue = issue ?: "",
        status = status ?: ""
    )
}
