package id.my.gradien.cloud.nodes.detail.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.my.gradien.cloud.nodes.detail.presentation.TimeRange

@Composable
fun HistoricalChartCard(
    pm25Value: Float,
    selectedTimeRange: TimeRange,
    sensorHistory: List<Pair<String, Float>>,
    onTimeRangeSelect: (TimeRange) -> Unit,
    modifier: Modifier = Modifier
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val fixedColor = Color(0xFFA4D397)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Top Row Header & Filters
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "PM2.5 HISTORICAL DATA",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        ),
                        color = primaryColor
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = pm25Value.toString(),
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "µg/m³",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Time Range Pills Row
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                    border = androidx.compose.foundation.BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        TimeRange.entries.forEach { range ->
                            val isSelected = range == selectedTimeRange
                            Surface(
                                onClick = { onTimeRangeSelect(range) },
                                shape = RoundedCornerShape(6.dp),
                                color = if (isSelected) MaterialTheme.colorScheme.surface else Color.Transparent,
                                shadowElevation = if (isSelected) 1.dp else 0.dp
                            ) {
                                Text(
                                    text = range.label,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                    ),
                                    color = if (isSelected) primaryColor else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Canvas Chart Rendering
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val width = size.width
                    val height = size.height

                    if (sensorHistory.isNotEmpty()) {
                        val maxVal = (sensorHistory.maxOfOrNull { it.second } ?: 50f).coerceAtLeast(30f)
                        val minVal = 0f

                        val points = sensorHistory.mapIndexed { index, pair ->
                            val x = (index.toFloat() / (sensorHistory.size - 1).coerceAtLeast(1)) * width
                            val y = height - ((pair.second - minVal) / (maxVal - minVal)) * height
                            x to y
                        }

                        // Build Gradient Path
                        val fillPath = Path().apply {
                            moveTo(0f, height)
                            points.forEach { (x, y) -> lineTo(x, y) }
                            lineTo(width, height)
                            close()
                        }

                        drawPath(
                            path = fillPath,
                            brush = Brush.verticalGradient(
                                colors = listOf(fixedColor.copy(alpha = 0.5f), fixedColor.copy(alpha = 0.0f))
                            )
                        )

                        // Build Line Path
                        val linePath = Path().apply {
                            points.forEachIndexed { i, (x, y) ->
                                if (i == 0) moveTo(x, y) else lineTo(x, y)
                            }
                        }

                        drawPath(
                            path = linePath,
                            color = primaryColor,
                            style = Stroke(width = 3.dp.toPx())
                        )

                        // Draw End Point Dot
                        points.lastOrNull()?.let { (x, y) ->
                            drawCircle(
                                color = primaryColor,
                                radius = 6.dp.toPx(),
                                center = androidx.compose.ui.geometry.Offset(x, y)
                            )
                            drawCircle(
                                color = fixedColor.copy(alpha = 0.5f),
                                radius = 12.dp.toPx(),
                                center = androidx.compose.ui.geometry.Offset(x, y)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // X-Axis Timestamps Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                sensorHistory.forEach { (label, _) ->
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}
