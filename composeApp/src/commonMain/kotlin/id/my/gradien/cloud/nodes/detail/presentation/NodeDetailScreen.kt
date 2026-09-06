package id.my.gradien.cloud.nodes.detail.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import trapgradienmobile.composeapp.generated.resources.Res
import trapgradienmobile.composeapp.generated.resources.device_controls_title
import trapgradienmobile.composeapp.generated.resources.status_offline
import trapgradienmobile.composeapp.generated.resources.status_online
import id.my.gradien.cloud.core.ui.theme.AppTheme
import id.my.gradien.cloud.nodes.detail.presentation.components.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NodeDetailScreen(
    state: NodeDetailState,
    onEvent: (NodeDetailEvent) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
                    NodeHeader(
                        title = state.nodeName,
                        subtitle = "${state.location} • ${if (state.isOnline) stringResource(Res.string.status_online) else stringResource(Res.string.status_offline)}",
                        badgeText = state.statusBadge,
                        onBackClick = {
                            onNavigateBack()
                        }
                    )
                }

                // Historical Chart Card
                item {
                    HistoricalChartCard(
                        pm25Value = state.currentPm25,
                        selectedTimeRange = state.selectedTimeRange,
                        sensorHistory = state.sensorHistory,
                        onTimeRangeSelect = { onEvent(NodeDetailEvent.OnTimeRangeSelected(it)) }
                    )
                }

                // Section Header: Device Controls
                item {
                    Text(
                        text = stringResource(Res.string.device_controls_title),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                // Power Control
                item {
                    PowerControlCard(
                        isPowerOn = state.isPowerOn,
                        onPowerToggle = { onEvent(NodeDetailEvent.OnPowerToggled(it)) }
                    )
                }

                // Ionizer Control
                item {
                    IonizerControlCard(
                        isIonizerOn = state.isIonizerOn,
                        selectedMode = state.ionizerMode,
                        onIonizerToggle = { onEvent(NodeDetailEvent.OnIonizerToggled(it)) },
                        onModeSelect = { onEvent(NodeDetailEvent.OnIonizerModeSelected(it)) }
                    )
                }

                // Fan Speed Control
                item {
                    FanSpeedCard(
                        currentSpeed = state.fanSpeed,
                        onSpeedChange = { onEvent(NodeDetailEvent.OnFanSpeedChanged(it)) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun NodeDetailScreenPreview() {
    AppTheme {
        NodeDetailScreen(
            state = NodeDetailState(),
            onEvent = {},
            onNavigateBack = {}
        )
    }
}
