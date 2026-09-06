package id.my.gradien.cloud.nodes.list.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource
import trapgradienmobile.composeapp.generated.resources.Res
import trapgradienmobile.composeapp.generated.resources.aqi_label
import trapgradienmobile.composeapp.generated.resources.battery_label
import trapgradienmobile.composeapp.generated.resources.percent_format
import trapgradienmobile.composeapp.generated.resources.status_alert
import trapgradienmobile.composeapp.generated.resources.status_not_available
import trapgradienmobile.composeapp.generated.resources.status_offline
import trapgradienmobile.composeapp.generated.resources.status_online
import id.my.gradien.cloud.nodes.list.presentation.models.NodeItemUi
import id.my.gradien.cloud.nodes.list.presentation.models.NodeStatusUi

@Composable
fun NodeCardItem(
    node: NodeItemUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (statusColor, statusBgColor, statusTextColor, statusText) = when (node.status) {
        NodeStatusUi.ONLINE -> Quadruple(
            Color(0xFF526442),
            Color(0xFFD2E6BC).copy(alpha = 0.4f),
            Color(0xFF275022),
            stringResource(Res.string.status_online).uppercase()
        )
        NodeStatusUi.OFFLINE -> Quadruple(
            Color(0xFF72796E),
            Color(0xFFE1E3E2).copy(alpha = 0.6f),
            Color(0xFF42493F),
            stringResource(Res.string.status_offline).uppercase()
        )
        NodeStatusUi.ALERT -> Quadruple(
            Color(0xFFBA1A1A),
            Color(0xFFFFDAD6),
            Color(0xFF93000A),
            stringResource(Res.string.status_alert).uppercase()
        )
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (node.status == NodeStatusUi.ALERT) {
                Color(0xFFFFDAD6).copy(alpha = 0.15f)
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            // Left Status Color Accent Bar
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .fillMaxHeight()
                    .align(Alignment.CenterStart)
                    .background(statusColor)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                // Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = node.name,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = node.location,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    // Status Badge
                    Surface(
                        shape = CircleShape,
                        color = statusBgColor,
                        border = BorderStroke(
                            width = 1.dp,
                            color = statusColor.copy(alpha = 0.3f)
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(statusColor)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = statusText,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                ),
                                color = statusTextColor
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Bottom Metrics Grid
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // AQI Metric Container
                    MetricTile(
                        label = stringResource(Res.string.aqi_label),
                        value = node.aqi?.toString() ?: "--",
                        subtitle = node.aqiLabel ?: (if (node.status == NodeStatusUi.OFFLINE) stringResource(Res.string.status_offline) else stringResource(Res.string.status_not_available)),
                        isAlert = node.status == NodeStatusUi.ALERT,
                        modifier = Modifier.weight(1f)
                    )

                    // Battery Metric Container
                    val batteryIcon = when {
                        node.batteryPercent == null || node.batteryPercent == 0 -> Icons.Default.Battery0Bar
                        node.batteryPercent < 30 -> Icons.Default.Battery2Bar
                        node.batteryPercent < 60 -> Icons.Default.Battery4Bar
                        node.batteryPercent < 90 -> Icons.Default.Battery5Bar
                        else -> Icons.Default.BatteryFull
                    }

                    MetricTile(
                        label = stringResource(Res.string.battery_label),
                        value = if (node.batteryPercent != null) stringResource(Res.string.percent_format, node.batteryPercent) else "--",
                        trailingIcon = batteryIcon,
                        isAlert = false,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

private data class Quadruple<A, B, C, D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
)
