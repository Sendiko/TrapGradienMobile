package id.my.gradien.cloud.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.my.gradien.cloud.core.ui.theme.AppTheme
import id.my.gradien.cloud.core.ui.components.GradienTopBar
import id.my.gradien.cloud.home.presentation.components.*
import id.my.gradien.cloud.nodes.domain.models.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreenContent(
    state: HomeState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Dashboard Section
        state.primaryNode?.let { node ->
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val field1Config = node.config["field1"]
                        val field1Value = state.latestSensorData?.fields?.get("field1")?.toFloatOrNull() ?: 0f
                        
                        AirQualityGauge(
                            value = field1Value,
                            maxValue = field1Config?.scale?.max?.toFloatOrNull() ?: 100f,
                            title = field1Config?.title ?: "Current Air Quality",
                            subtitle = "Maximum"
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        val threshold = field1Config?.thresholds?.find { 
                            val from = it.from.toFloatOrNull() ?: 0f
                            val to = it.to.toFloatOrNull() ?: 0f
                            field1Value >= from && field1Value <= to
                        }
                        
                        threshold?.let {
                            PurificationPill(threshold = it)
                        }
                    }
                }
            }

            // Telemetry Grid
            item {
                val telemetryItems = node.config.filterKeys { it != "field1" }
                    .mapNotNull { (key, config) ->
                        val value = state.latestSensorData?.fields?.get(key) ?: return@mapNotNull null
                        TelemetryItem(
                            label = config.title,
                            value = value,
                            unit = config.yaxis
                        )
                    }
                
                TelemetryGrid(items = telemetryItems)
            }
        }

        // Alerts Section
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Warning, 
                    contentDescription = null, 
                    tint = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Active Alerts",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        if (state.isLoadingAlerts) {
            item {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        } else if (state.alerts.isEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Box(modifier = Modifier.padding(16.dp)) {
                        Text(text = "All systems operational. No active alerts.")
                    }
                }
            }
        } else {
            items(state.alerts) { issue ->
                AlertItem(
                    issue = issue,
                    onAcknowledge = { /* TODO */ }
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenFullPreview() {
    AppTheme {
        val state = HomeState(
            name = "User",
            primaryNode = Node(
                id = "27",
                nodeId = "b30c7928",
                nodeKey = "fe2a6b06",
                name = "Lab P320",
                type = "TrapGradien",
                icon = "TrapGradien.png",
                config = mapOf(
                    "field1" to NodeConfig(
                        icon = "wind",
                        scale = Scale("0", "4"),
                        title = "Purification Status",
                        xaxis = "Time",
                        yaxis = "Purification Level",
                        thresholds = listOf(
                            Threshold("0", "0", "check-circle", "#2ecc71", "No Purification"),
                            Threshold("4", "4", "fan", "#e74c3c", "Maximum Purification")
                        )
                    ),
                    "field2" to NodeConfig(title = "PM2.5", yaxis = "μg/m³", icon = "", scale = Scale("0", "100"), xaxis = "", thresholds = emptyList()),
                    "field3" to NodeConfig(title = "VOCs", yaxis = "mg/m³", icon = "", scale = Scale("0", "10"), xaxis = "", thresholds = emptyList()),
                    "field4" to NodeConfig(title = "CO2", yaxis = "ppm", icon = "", scale = Scale("0", "2000"), xaxis = "", thresholds = emptyList()),
                    "field5" to NodeConfig(title = "Temp", yaxis = "Celsius", icon = "", scale = Scale("0", "50"), xaxis = "", thresholds = emptyList())
                )
            ),
            latestSensorData = SensorData(
                id = "1",
                createdAt = "2025-12-02 07:06:26",
                fields = mapOf(
                    "field1" to "4",
                    "field2" to "12",
                    "field3" to "0.4",
                    "field4" to "420",
                    "field5" to "22"
                )
            ),
            alerts = listOf(
                NodeIssue(
                    time = "2025-12-02 23:12:58",
                    issue = "Low Battery",
                    status = "Unresolved"
                )
            )
        )
        Scaffold(
            topBar = {
                GradienTopBar(
                    onAlertClick = {}
                )
            }
        ) { padding ->
            Surface(modifier = Modifier.padding(padding)) {
                HomeScreenContent(state = state)
            }
        }
    }
}
