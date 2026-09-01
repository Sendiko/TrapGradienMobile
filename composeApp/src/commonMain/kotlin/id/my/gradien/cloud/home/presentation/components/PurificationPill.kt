package id.my.gradien.cloud.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import id.my.gradien.cloud.nodes.domain.models.Threshold

@Composable
fun PurificationPill(
    threshold: Threshold,
    modifier: Modifier = Modifier
) {
    val backgroundColor = try {
        Color(parseHexColor(threshold.color)).copy(alpha = 0.1f)
    } catch (e: Exception) {
        MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.1f)
    }
    
    val contentColor = try {
        Color(parseHexColor(threshold.color))
    } catch (e: Exception) {
        MaterialTheme.colorScheme.onSecondaryContainer
    }

    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(contentColor)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = threshold.label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

private fun parseHexColor(hex: String): Long {
    val cleanHex = hex.removePrefix("#")
    return cleanHex.toLong(16) or 0xFF000000L
}
