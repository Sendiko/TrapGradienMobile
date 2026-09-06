package id.my.gradien.cloud.nodes.list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import trapgradienmobile.composeapp.generated.resources.Res
import trapgradienmobile.composeapp.generated.resources.no_nodes_found
import id.my.gradien.cloud.core.navigation.NodeScreen
import id.my.gradien.cloud.core.ui.theme.AppTheme
import id.my.gradien.cloud.nodes.list.presentation.components.NodeCardItem
import id.my.gradien.cloud.nodes.list.presentation.components.NodeFilterRow
import id.my.gradien.cloud.nodes.list.presentation.components.NodeSearchBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NodeListScreen(
    modifier: Modifier = Modifier,
    state: NodeListState,
    onEvent: (NodeListEvent) -> Unit,
    onNavigate: (Any) -> Unit
) {
    PullToRefreshBox(
        isRefreshing = state.isLoading,
        onRefresh = { onEvent(NodeListEvent.OnRefresh) },
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            /* Search Input Bar */
            item {
                NodeSearchBar(
                    query = state.searchQuery,
                    onQueryChange = { onEvent(NodeListEvent.OnSearchQueryChanged(it)) }
                )
            }

            /* Horizontal Filter Chips */
            item {
                NodeFilterRow(
                    selectedFilter = state.selectedFilter,
                    onFilterSelect = { onEvent(NodeListEvent.OnFilterSelected(it)) }
                )
            }

            /* Loading state */
            if (state.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }
            } else if (state.filteredNodes.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(Res.string.no_nodes_found),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            } else {
                items(state.filteredNodes, key = { it.id }) { node ->
                    NodeCardItem(
                        node = node,
                        onClick = { onNavigate(NodeScreen(node.nodeId, node.name)) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun NodeListScreenPreview() {
    AppTheme {
        NodeListScreen(
            state = NodeListState(),
            onEvent = {  },
            onNavigate = {  }
        )
    }
}
