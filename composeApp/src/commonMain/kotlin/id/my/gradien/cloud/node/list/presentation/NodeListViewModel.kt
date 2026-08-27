package id.my.gradien.cloud.node.list.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NodeListViewModel : ViewModel() {
    private val _state = MutableStateFlow(NodeListState())
    val state = _state.asStateFlow()
}
