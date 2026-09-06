package id.my.gradien.cloud.clusters.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


import org.jetbrains.compose.resources.stringResource
import trapgradienmobile.composeapp.generated.resources.Res
import trapgradienmobile.composeapp.generated.resources.status_alert
import trapgradienmobile.composeapp.generated.resources.status_online

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
            text = if (isAlert) stringResource(Res.string.status_alert) else stringResource(Res.string.status_online),
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = contentColor
        )
    }
}
