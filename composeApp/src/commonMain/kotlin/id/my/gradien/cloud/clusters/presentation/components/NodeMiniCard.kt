package id.my.gradien.cloud.clusters.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.my.gradien.cloud.clusters.presentation.models.NodeItemUiModel
import id.my.gradien.cloud.clusters.presentation.models.NodeStatus

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