package id.my.gradien.cloud.nodes.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.gradien.cloud.clusters.domain.ClusterRepository
import id.my.gradien.cloud.core.network.utils.getOrNull
import id.my.gradien.cloud.core.network.utils.onSuccess
import id.my.gradien.cloud.core.session.SessionManager
import id.my.gradien.cloud.nodes.core.domain.NodeRepository
import id.my.gradien.cloud.nodes.list.presentation.models.NodeFilter
import id.my.gradien.cloud.nodes.list.presentation.models.NodeItemUi
import id.my.gradien.cloud.nodes.list.presentation.models.NodeStatusUi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NodeListViewModel(
    private val sessionManager: SessionManager,
    private val clusterRepository: ClusterRepository,
    private val nodeRepository: NodeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(NodeListState())
    val state: StateFlow<NodeListState> = _state.asStateFlow()

    fun onEvent(event: NodeListEvent) {
        when (event) {
            NodeListEvent.OnLoadData -> loadNodes()
            NodeListEvent.OnRefresh -> refresh()
            is NodeListEvent.OnSearchQueryChanged -> onSearchQueryChange(event.query)
            is NodeListEvent.OnFilterSelected -> onFilterSelect(event.filter)
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            val ids = sessionManager.clusterIds.first()
            val email = sessionManager.email.first()
            val password = sessionManager.password.first()

            if (ids.isNotEmpty() && email != null && password != null) {
                _state.update { it.copy(isLoading = true) }
                val loadedNodes = mutableListOf<NodeItemUi>()

                val deferredClusters = ids.map { id ->
                    async {
                        clusterRepository.getClusterDetails(email, password, id)
                            .onSuccess { cluster ->
                                val nodeItems = cluster.nodes.map { nodeId ->
                                    async {
                                        var itemUi: NodeItemUi? = null
                                        nodeRepository.getNodeDetails(email, password, nodeId)
                                            .onSuccess { node ->
                                                val latestData = nodeRepository.getSensorData(node.nodeId, node.nodeKey, limit = 1).getOrNull()?.firstOrNull()
                                                val logs = nodeRepository.getNodeLogs(node.id).getOrNull()

                                                val isAlert = logs?.issues?.any { it.status.equals("Unresolved", ignoreCase = true) } == true
                                                val isOffline = latestData == null

                                                val status = when {
                                                    isAlert -> NodeStatusUi.ALERT
                                                    isOffline -> NodeStatusUi.OFFLINE
                                                    else -> NodeStatusUi.ONLINE
                                                }

                                                val rawAqi = latestData?.fields?.get("field1")?.toIntOrNull()
                                                val aqiLabel = when {
                                                    rawAqi == null -> null
                                                    rawAqi <= 50 -> "Good"
                                                    rawAqi <= 100 -> "Moderate"
                                                    else -> "Poor"
                                                }

                                                val battery = latestData?.fields?.get("field2")?.toIntOrNull() ?: if (status == NodeStatusUi.ONLINE) 85 else 0

                                                itemUi = NodeItemUi(
                                                    id = node.id,
                                                    nodeId = node.nodeId,
                                                    name = node.name,
                                                    location = cluster.name,
                                                    status = status,
                                                    aqi = rawAqi,
                                                    aqiLabel = aqiLabel,
                                                    batteryPercent = battery
                                                )
                                            }
                                        itemUi
                                    }
                                }.awaitAll().filterNotNull()
                                loadedNodes.addAll(nodeItems)
                            }
                    }
                }

                deferredClusters.awaitAll()
                _state.update { it.copy(nodes = loadedNodes, isLoading = false) }
            }
        }
    }

    private fun onSearchQueryChange(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }

    private fun onFilterSelect(filter: NodeFilter) {
        _state.update { it.copy(selectedFilter = filter) }
    }

    private fun loadNodes() {
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
                    val loadedNodes = mutableListOf<NodeItemUi>()

                    val deferredClusters = ids.map { id ->
                        async {
                            clusterRepository.getClusterDetails(email, password, id)
                                .onSuccess { cluster ->
                                    val nodeItems = cluster.nodes.map { nodeId ->
                                        async {
                                            var itemUi: NodeItemUi? = null
                                            nodeRepository.getNodeDetails(email, password, nodeId)
                                                .onSuccess { node ->
                                                    val latestData = nodeRepository.getSensorData(node.nodeId, node.nodeKey, limit = 1).getOrNull()?.firstOrNull()
                                                    val logs = nodeRepository.getNodeLogs(node.id).getOrNull()

                                                    val isAlert = logs?.issues?.any { it.status.equals("Unresolved", ignoreCase = true) } == true
                                                    val isOffline = latestData == null

                                                    val status = when {
                                                        isAlert -> NodeStatusUi.ALERT
                                                        isOffline -> NodeStatusUi.OFFLINE
                                                        else -> NodeStatusUi.ONLINE
                                                    }

                                                    val rawAqi = latestData?.fields?.get("field1")?.toIntOrNull()
                                                    val aqiLabel = when {
                                                        rawAqi == null -> null
                                                        rawAqi <= 50 -> "Good"
                                                        rawAqi <= 100 -> "Moderate"
                                                        else -> "Poor"
                                                    }

                                                    val battery = latestData?.fields?.get("field2")?.toIntOrNull() ?: if (status == NodeStatusUi.ONLINE) 85 else 0

                                                    itemUi = NodeItemUi(
                                                        id = node.id,
                                                        nodeId = node.nodeId,
                                                        name = node.name,
                                                        location = cluster.name,
                                                        status = status,
                                                        aqi = rawAqi,
                                                        aqiLabel = aqiLabel,
                                                        batteryPercent = battery
                                                    )
                                                }
                                            itemUi
                                        }
                                    }.awaitAll().filterNotNull()
                                    loadedNodes.addAll(nodeItems)
                                }
                        }
                    }

                    deferredClusters.awaitAll()
                    if (loadedNodes.isNotEmpty()) {
                        _state.update { it.copy(nodes = loadedNodes, isLoading = false) }
                    } else {
                        _state.update { it.copy(isLoading = false) }
                    }
                }
            }
        }
    }
}
