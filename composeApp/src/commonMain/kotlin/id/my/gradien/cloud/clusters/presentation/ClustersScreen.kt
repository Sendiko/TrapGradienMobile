package id.my.gradien.cloud.clusters.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import trapgradienmobile.composeapp.generated.resources.Res
import trapgradienmobile.composeapp.generated.resources.cluster_management_subtitle
import trapgradienmobile.composeapp.generated.resources.cluster_management_title
import trapgradienmobile.composeapp.generated.resources.no_clusters_message
import id.my.gradien.cloud.clusters.domain.models.Cluster
import id.my.gradien.cloud.clusters.presentation.components.ClusterCard
import id.my.gradien.cloud.clusters.presentation.models.ClusterUiModel
import id.my.gradien.cloud.clusters.presentation.models.NodeItemUiModel
import id.my.gradien.cloud.core.ui.components.TrapGradienTopBar
import id.my.gradien.cloud.core.ui.theme.AppTheme
import id.my.gradien.cloud.nodes.core.domain.models.Node
import id.my.gradien.cloud.nodes.core.domain.models.NodeIssue
import id.my.gradien.cloud.nodes.core.domain.models.NodeLog
import id.my.gradien.cloud.nodes.core.domain.models.SensorData
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClustersScreen(
    state: ClustersState,
    onEvent: (ClustersEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(state.clusters) {
        if (state.clusters.isEmpty())
            onEvent(ClustersEvent.OnLoadData)
    }

    Scaffold(
        topBar = {
            TrapGradienTopBar(
                onAlertClick = { /* TODO */ }
            )
        }
    ) {
        PullToRefreshBox(
            isRefreshing = state.isLoading,
            onRefresh = { onEvent(ClustersEvent.OnLoadData) },
            modifier = modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    top = it.calculateTopPadding() + 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item {
                    Column(modifier = Modifier.padding(bottom = 8.dp)) {
                        Text(
                            text = stringResource(Res.string.cluster_management_title),
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = stringResource(Res.string.cluster_management_subtitle),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                if (state.isLoading) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                } else if (state.clusters.isEmpty()) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Box(modifier = Modifier.padding(16.dp)) {
                                Text(text = stringResource(Res.string.no_clusters_message))
                            }
                        }
                    }
                } else {
                    items(state.clusters) { clusterUi ->
                        ClusterCard(
                            clusterUi = clusterUi,
                            onToggleExpand = { onEvent(ClustersEvent.OnClusterExpand(clusterUi)) }
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun ClustersScreenPreview() {
    AppTheme {
        Surface {
            ClustersScreen(
                state = ClustersState(
                    clusters = listOf(
                        ClusterUiModel(
                            cluster = Cluster(
                                id = "8",
                                clusterId = "cl-ny-001",
                                clusterKey = "b732cc9c",
                                name = "Central Park Cluster",
                                type = "Stationary",
                                longitude = "0",
                                latitude = "0",
                                description = "Kumpulan TrapGradien di TelU",
                                icon = "stationary.jpg",
                                nodes = listOf("b30c7928"),
                                createdAt = "",
                                updatedAt = ""
                            ),
                            nodeDetails = listOf(
                                NodeItemUiModel(
                                    node = Node(
                                        "1",
                                        "node-a",
                                        "key",
                                        "Node A (North Gate)",
                                        "Type",
                                        "",
                                        emptyMap()
                                    ),
                                    latestData = SensorData("1", "", mapOf("field1" to "42"))
                                ),
                                NodeItemUiModel(
                                    node = Node(
                                        "2",
                                        "node-b",
                                        "key",
                                        "Node B (Great Lawn)",
                                        "Type",
                                        "",
                                        emptyMap()
                                    ),
                                    latestData = SensorData("2", "", mapOf("field1" to "42"))
                                ),
                                NodeItemUiModel(
                                    node = Node(
                                        "3",
                                        "node-c",
                                        "key",
                                        "Node C (Carousel)",
                                        "Type",
                                        "",
                                        emptyMap()
                                    ),
                                    logs = NodeLog(
                                        listOf(
                                            NodeIssue(
                                                "",
                                                "Maintenance",
                                                "Unresolved"
                                            )
                                        ), emptyList()
                                    )
                                )
                            ),
                            isExpanded = true
                        ),
                        ClusterUiModel(
                            cluster = Cluster(
                                id = "9",
                                clusterId = "cl-ny-002",
                                clusterKey = "b732cc9d",
                                name = "Downtown Hub",
                                type = "Stationary",
                                longitude = "0",
                                latitude = "0",
                                description = "Downtown deployment",
                                icon = "stationary.jpg",
                                nodes = listOf("b30c7929"),
                                createdAt = "",
                                updatedAt = ""
                            ),
                            nodeDetails = listOf(
                                NodeItemUiModel(
                                    node = Node(
                                        "4",
                                        "node-d",
                                        "key",
                                        "Node D",
                                        "Type",
                                        "",
                                        emptyMap()
                                    ),
                                    latestData = SensorData("4", "", mapOf("field1" to "115")),
                                    logs = NodeLog(
                                        listOf(NodeIssue("", "High Heat", "Unresolved")),
                                        emptyList()
                                    )
                                )
                            ),
                            isExpanded = false
                        )
                    )
                ),
                onEvent = { }
            )
        }
    }
}
