package id.my.gradien.cloud.clusters.presentation

import id.my.gradien.cloud.clusters.presentation.models.ClusterUiModel

data class ClustersState(
    val clusters: List<ClusterUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
