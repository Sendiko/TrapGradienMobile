package id.my.gradien.cloud.nodes.list.presentation

import id.my.gradien.cloud.nodes.list.presentation.models.NodeFilter

sealed interface NodeListEvent {
    data object OnLoadData: NodeListEvent
    data object OnRefresh: NodeListEvent
    data class OnSearchQueryChanged(val query: String) : NodeListEvent
    data class OnFilterSelected(val filter: NodeFilter) : NodeListEvent
}
