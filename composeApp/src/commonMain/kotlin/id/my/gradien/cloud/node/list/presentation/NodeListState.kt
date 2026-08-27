package id.my.gradien.cloud.node.list.presentation

import id.my.gradien.cloud.node.domain.model.Node

data class NodeListState(
    val nodes: List<Node> = listOf(
        Node("Alat Lorem #1", "Cikoneng, Bojongsoang, Bandung"),
        Node("Alat Lorem #1", "Cikoneng, Bojongsoang, Bandung"),
        Node("Alat Lorem #1", "Cikoneng, Bojongsoang, Bandung"),
        Node("Alat Lorem #1", "Cikoneng, Bojongsoang, Bandung")
    )
)
