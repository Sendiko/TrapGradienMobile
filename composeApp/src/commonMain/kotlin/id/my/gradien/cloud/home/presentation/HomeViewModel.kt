package id.my.gradien.cloud.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.gradien.cloud.core.network.utils.onSuccess
import id.my.gradien.cloud.core.session.SessionManager
import id.my.gradien.cloud.nodes.domain.NodeRepository
import id.my.gradien.cloud.nodes.domain.models.Node
import id.my.gradien.cloud.nodes.domain.models.NodeIssue
import id.my.gradien.cloud.nodes.domain.models.SensorData
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val sessionManager: SessionManager,
    private val nodeRepository: NodeRepository
) : ViewModel() {

    private val _alertsState = MutableStateFlow<List<NodeIssue>>(emptyList())
    private val _isLoadingAlerts = MutableStateFlow(false)
    private val _primaryNode = MutableStateFlow<Node?>(null)
    private val _latestSensorData = MutableStateFlow<SensorData?>(null)
    private val _isLoadingNodeData = MutableStateFlow(false)

    val state: StateFlow<HomeState> = combine(
        sessionManager.name,
        sessionManager.email,
        _alertsState,
        _isLoadingAlerts,
        _primaryNode,
        _latestSensorData,
        _isLoadingNodeData
    ) { flows ->
        HomeState(
            name = flows[0] as String?,
            email = flows[1] as String?,
            alerts = flows[2] as List<NodeIssue>,
            isLoadingAlerts = flows[3] as Boolean,
            primaryNode = flows[4] as Node?,
            latestSensorData = flows[5] as SensorData?,
            isLoadingNodeData = flows[6] as Boolean
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeState()
    )

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            combine(
                sessionManager.nodeIds,
                sessionManager.email,
                sessionManager.password
            ) { nodes, email, password ->
                Triple(nodes, email, password)
            }.collectLatest { (nodes, email, password) ->
                if (nodes.isNotEmpty() && email != null && password != null) {
                    fetchAlerts(nodes, email, password)
                    fetchPrimaryNodeData(nodes.first(), email, password)
                }
            }
        }
    }

    private suspend fun fetchPrimaryNodeData(nodeId: String, email: String, password: String) {
        _isLoadingNodeData.value = true
        nodeRepository.getNodeDetails(email, password, nodeId)
            .onSuccess { node ->
                _primaryNode.value = node
                // Start polling sensor data
                startSensorDataPolling(node)
            }
        _isLoadingNodeData.value = false
    }

    private fun startSensorDataPolling(node: Node) {
        viewModelScope.launch {
            while (true) {
                nodeRepository.getSensorData(node.nodeId, node.nodeKey, limit = 1)
                    .onSuccess { data ->
                        _latestSensorData.value = data.firstOrNull()
                    }
                delay(10_000) // Poll every 10 seconds
            }
        }
    }

    private suspend fun fetchAlerts(nodes: List<String>, email: String, password: String) {
        _isLoadingAlerts.value = true
        
        val deferredAlerts = nodes.map { nodeId ->
            viewModelScope.async {
                val alerts = mutableListOf<NodeIssue>()
                nodeRepository.getNodeDetails(email, password, nodeId)
                    .onSuccess { nodeDetails ->
                        nodeRepository.getNodeLogs(nodeDetails.id)
                            .onSuccess { nodeLog ->
                                alerts.addAll(nodeLog.issues)
                            }
                    }
                alerts
            }
        }

        val allAlerts = deferredAlerts.awaitAll().flatten()
        
        // Sort by time descending (latest first)
        _alertsState.value = allAlerts.sortedByDescending { it.time }
        _isLoadingAlerts.value = false
    }
}
