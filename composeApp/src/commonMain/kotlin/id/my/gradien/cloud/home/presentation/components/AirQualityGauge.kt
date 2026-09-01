package id.my.gradien.cloud.home.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AirQualityGauge(
    value: Float,
    maxValue: Float,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    val progress = if (maxValue > 0) (value / maxValue).coerceIn(0f, 1f) else 0f

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(200.dp)
        ) {
            val primaryColor = MaterialTheme.colorScheme.primary
            Canvas(modifier = Modifier.fillMaxSize()) {
                val strokeWidth = 24.dp.toPx()
                val center = size.center
                val radius = (size.minDimension - strokeWidth) / 2

                // Background Track
                drawCircle(
                    color = primaryColor.copy(alpha = 0.1f),
                    radius = radius,
                    center = center,
                    style = Stroke(width = strokeWidth)
                )

                // Progress Arc
                drawArc(
                    color = primaryColor,
                    startAngle = -90f,
                    sweepAngle = 360f * progress,
                    useCenter = false,
                    topLeft = Offset(center.x - radius, center.y - radius),
                    size = Size(radius * 2, radius * 2),
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${(progress * 100).toInt()}%",
                    style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.ExtraBold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
