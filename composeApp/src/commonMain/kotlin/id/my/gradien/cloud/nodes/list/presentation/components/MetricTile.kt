package id.my.gradien.cloud.nodes.list.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MetricTile(
    label: String,
    value: String,
    subtitle: String? = null,
    trailingIcon: ImageVector? = null,
    isAlert: Boolean = false,
    modifier: Modifier = Modifier
) {
    val bgColor = if (isAlert) Color(0xFFFFDAD6).copy(alpha = 0.5f) else MaterialTheme.colorScheme.surfaceContainerLow
    val borderColor = if (isAlert) Color(0xFFBA1A1A).copy(alpha = 0.3f) else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
    val labelColor = if (isAlert) Color(0xFFBA1A1A) else MaterialTheme.colorScheme.primary
    val valueColor = if (isAlert) Color(0xFFBA1A1A) else MaterialTheme.colorScheme.onSurface

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        color = bgColor,
        border = BorderStroke(1.dp, borderColor)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                ),
                color = labelColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = valueColor
                )
                if (subtitle != null) {
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isAlert) Color(0xFFBA1A1A) else MaterialTheme.colorScheme.outline
                    )
                }
                if (trailingIcon != null) {
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}
