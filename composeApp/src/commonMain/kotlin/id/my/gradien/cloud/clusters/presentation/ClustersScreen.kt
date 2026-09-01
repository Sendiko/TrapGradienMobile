package id.my.gradien.cloud.clusters.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.my.gradien.cloud.clusters.domain.models.Cluster
import id.my.gradien.cloud.clusters.presentation.models.ClusterUiModel
import id.my.gradien.cloud.clusters.presentation.models.NodeItemUiModel
import id.my.gradien.cloud.clusters.presentation.models.NodeStatus
import id.my.gradien.cloud.core.ui.theme.AppTheme
import id.my.gradien.cloud.nodes.domain.models.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ClustersScreen(
    state: ClustersState,
    onToggleExpand: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.White
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Column(modifier = Modifier.padding(bottom = 8.dp)) {
                    Text(
                        text = "Cluster Management",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Monitor and manage stationary telemetry clusters.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (state.isLoading) {
                item {
                    Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
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
                            Text(text = "No clusters assigned to your account.")
                        }
                    }
                }
            } else {
                items(state.clusters) { clusterUi ->
                    ClusterItem(
                        clusterUi = clusterUi,
                        onToggleExpand = { onToggleExpand(clusterUi.cluster.clusterId) }
                    )
                }
            }
        }
    }
}

@Composable
fun ClusterItem(
    clusterUi: ClusterUiModel,
    onToggleExpand: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isAlert = clusterUi.nodeDetails.any { it.status == NodeStatus.Maintenance }
    
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onToggleExpand() }
            .animateContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = if (isAlert) Color(0xFFFFF8F8) else MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = clusterUi.cluster.name,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "ID: ${clusterUi.cluster.clusterId.uppercase()}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    StatusChip(isAlert = isAlert)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = if (clusterUi.isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Summary Info
            Row(modifier = Modifier.fillMaxWidth()) {
                SummaryField(
                    label = "Nodes",
                    value = "${clusterUi.activeNodes} Active" + if (clusterUi.offlineNodes > 0) ", ${clusterUi.offlineNodes} Offline" else "",
                    modifier = Modifier.weight(1f)
                )
                SummaryField(
                    label = "Avg AQI",
                    value = "${clusterUi.averageAqi} (${clusterUi.aqiStatus})",
                    valueColor = if (isAlert) MaterialTheme.colorScheme.error else Color(0xFF275022),
                    modifier = Modifier.weight(1f)
                )
            }

            // Expanded Node List
            if (clusterUi.isExpanded) {
                Spacer(modifier = Modifier.height(24.dp))
                clusterUi.nodeDetails.forEach { nodeItem ->
                    NodeMiniCard(nodeItem = nodeItem)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun StatusChip(isAlert: Boolean) {
    val containerColor = if (isAlert) Color(0xFFFFDAD6) else Color(0xFF3E6837)
    val contentColor = if (isAlert) Color(0xFF93000A) else Color.White
    
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(containerColor)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (isAlert) Icons.Default.Error else Icons.Default.CheckCircle,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = contentColor
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = if (isAlert) "Alert" else "Online",
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = contentColor
        )
    }
}

@Composable
fun SummaryField(
    label: String,
    value: String,
    valueColor: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = valueColor
        )
    }
}

@Composable
fun NodeMiniCard(nodeItem: NodeItemUiModel) {
    val isMaintenance = nodeItem.status == NodeStatus.Maintenance
    val containerColor = if (isMaintenance) Color(0xFFFFDAD6) else Color(0xFFF2F4F3)
    val contentColor = if (isMaintenance) Color(0xFF93000A) else MaterialTheme.colorScheme.onSurface
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isMaintenance) Icons.Default.Warning else Icons.Default.Wifi,
                contentDescription = null,
                tint = contentColor
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = if (isMaintenance) "${nodeItem.node.name} - Maintenance Required" else nodeItem.node.name,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = contentColor,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.SignalCellularAlt,
                contentDescription = null,
                tint = contentColor.copy(alpha = 0.6f)
            )
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
                                    node = Node("1", "node-a", "key", "Node A (North Gate)", "Type", "", emptyMap()),
                                    latestData = SensorData("1", "", mapOf("field1" to "42"))
                                ),
                                NodeItemUiModel(
                                    node = Node("2", "node-b", "key", "Node B (Great Lawn)", "Type", "", emptyMap()),
                                    latestData = SensorData("2", "", mapOf("field1" to "42"))
                                ),
                                NodeItemUiModel(
                                    node = Node("3", "node-c", "key", "Node C (Carousel)", "Type", "", emptyMap()),
                                    logs = NodeLog(listOf(NodeIssue("", "Maintenance", "Unresolved")), emptyList())
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
                                    node = Node("4", "node-d", "key", "Node D", "Type", "", emptyMap()),
                                    latestData = SensorData("4", "", mapOf("field1" to "115")),
                                    logs = NodeLog(listOf(NodeIssue("", "High Heat", "Unresolved")), emptyList())
                                )
                            ),
                            isExpanded = false
                        )
                    )
                ),
                onToggleExpand = {}
            )
        }
    }
}
