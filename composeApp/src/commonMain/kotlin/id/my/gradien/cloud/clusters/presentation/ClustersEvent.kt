package id.my.gradien.cloud.clusters.presentation

import id.my.gradien.cloud.clusters.presentation.models.ClusterUiModel

sealed interface ClustersEvent {
    data object OnLoadData: ClustersEvent
    data object OnRefresh: ClustersEvent
    data class OnClusterExpand(val cluster: ClusterUiModel): ClustersEvent
}