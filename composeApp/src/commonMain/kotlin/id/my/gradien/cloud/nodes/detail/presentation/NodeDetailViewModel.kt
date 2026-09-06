package id.my.gradien.cloud.nodes.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.gradien.cloud.core.network.utils.onSuccess
import id.my.gradien.cloud.core.session.SessionManager
import id.my.gradien.cloud.nodes.core.domain.NodeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NodeDetailViewModel(
    private val sessionManager: SessionManager,
    private val nodeRepository: NodeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(NodeDetailState())
    val state: StateFlow<NodeDetailState> = _state.asStateFlow()

    fun initNode(id: String, key: String) {
        _state.update { it.copy(nodeId = id, nodeKey = key) }
        loadNodeDetail(id, key)
    }

    fun onEvent(event: NodeDetailEvent) {
        when (event) {
            is NodeDetailEvent.OnTimeRangeSelected -> _state.update { it.copy(selectedTimeRange = event.timeRange) }
            is NodeDetailEvent.OnPowerToggled -> _state.update { it.copy(isPowerOn = event.isPowerOn) }
            is NodeDetailEvent.OnIonizerToggled -> _state.update { it.copy(isIonizerOn = event.isIonizerOn) }
            is NodeDetailEvent.OnIonizerModeSelected -> _state.update { it.copy(ionizerMode = event.mode) }
            is NodeDetailEvent.OnFanSpeedChanged -> _state.update { it.copy(fanSpeed = event.speed) }
        }
    }

    private fun loadNodeDetail(id: String, key: String) {
        viewModelScope.launch {
            combine(
                sessionManager.email,
                sessionManager.password
            ) { email, password ->
                email to password
            }.collectLatest { (email, password) ->
                if (email != null && password != null) {
                    _state.update { it.copy(isLoading = true) }
                    nodeRepository.getNodeDetails(email, password, id)
                        .onSuccess { node ->
                            _state.update {
                                it.copy(
                                    nodeName = node.name,
                                    location = "${node.type} Unit",
                                    isLoading = false
                                )
                            }
                        }
                    
                    nodeRepository.getSensorData(id, key, limit = 10)
                        .onSuccess { sensorDataList ->
                            if (sensorDataList.isNotEmpty()) {
                                val latest = sensorDataList.first()
                                val pm25 = latest.fields["field2"]?.toFloatOrNull() ?: 12.4f
                                val history = sensorDataList.mapIndexed { _, data ->
                                    data.createdAt.takeLast(8) to (data.fields["field2"]?.toFloatOrNull() ?: 10f)
                                }.reversed()

                                _state.update {
                                    it.copy(
                                        currentPm25 = pm25,
                                        sensorHistory = if (history.isNotEmpty()) history else NodeDetailState.defaultChartData,
                                        isLoading = false
                                    )
                                }
                            }
                        }
                }
            }
        }
    }
}
