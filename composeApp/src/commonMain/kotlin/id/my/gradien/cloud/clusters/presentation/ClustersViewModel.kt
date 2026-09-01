package id.my.gradien.cloud.clusters.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.gradien.cloud.clusters.domain.ClusterRepository
import id.my.gradien.cloud.clusters.presentation.models.ClusterUiModel
import id.my.gradien.cloud.clusters.presentation.models.NodeItemUiModel
import id.my.gradien.cloud.core.network.utils.getOrNull
import id.my.gradien.cloud.core.network.utils.onSuccess
import id.my.gradien.cloud.core.session.SessionManager
import id.my.gradien.cloud.nodes.domain.NodeRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ClustersViewModel(
    private val sessionManager: SessionManager,
    private val clusterRepository: ClusterRepository,
    private val nodeRepository: NodeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ClustersState())
    val state = _state.asStateFlow()

    init {
        fetchClusters()
    }

    fun toggleExpand(clusterId: String) {
        _state.update { currentState ->
            currentState.copy(
                clusters = currentState.clusters.map {
                    if (it.cluster.clusterId == clusterId) it.copy(isExpanded = !it.isExpanded)
                    else it
                }
            )
        }
    }

    private fun fetchClusters() {
        viewModelScope.launch {
            combine(
                sessionManager.clusterIds,
                sessionManager.email,
                sessionManager.password
            ) { ids, email, password ->
                Triple(ids, email, password)
            }.collectLatest { (ids, email, password) ->
                if (ids.isNotEmpty() && email != null && password != null) {
                    _state.update { it.copy(isLoading = true) }
                    
                    val deferredClusters = ids.map { id ->
                        async {
                            var clusterUi: ClusterUiModel? = null
                            clusterRepository.getClusterDetails(email, password, id)
                                .onSuccess { cluster ->
                                    val nodeItems = cluster.nodes.map { nodeId ->
                                        async {
                                            var nodeItem: NodeItemUiModel? = null
                                            nodeRepository.getNodeDetails(email, password, nodeId)
                                                .onSuccess { node ->
                                                    val latestData = nodeRepository.getSensorData(node.nodeId, node.nodeKey, limit = 1).getOrNull()?.firstOrNull()
                                                    val logs = nodeRepository.getNodeLogs(node.id).getOrNull()
                                                    nodeItem = NodeItemUiModel(node, latestData, logs)
                                                }
                                            nodeItem
                                        }
                                    }.awaitAll().filterNotNull()
                                    
                                    clusterUi = ClusterUiModel(
                                        cluster = cluster,
                                        nodeDetails = nodeItems
                                    )
                                }
                            clusterUi
                        }
                    }
                    
                    val clusters = deferredClusters.awaitAll().filterNotNull()
                    _state.update { it.copy(clusters = clusters, isLoading = false) }
                }
            }
        }
    }
}
