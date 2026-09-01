package id.my.gradien.cloud.nodes.domain.models

data class NodeLog(
    val issues: List<NodeIssue>,
    val commands: List<String> // Commands are currently empty in example, representing as String for now
)

data class NodeIssue(
    val time: String,
    val issue: String,
    val status: String
)
