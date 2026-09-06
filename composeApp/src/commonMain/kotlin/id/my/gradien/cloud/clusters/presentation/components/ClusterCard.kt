package id.my.gradien.cloud.clusters.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.my.gradien.cloud.clusters.presentation.models.ClusterUiModel
import id.my.gradien.cloud.clusters.presentation.models.NodeStatus

@Composable
fun ClusterCard(
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