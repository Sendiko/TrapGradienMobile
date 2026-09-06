package id.my.gradien.cloud.nodes.detail.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BlurOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import org.jetbrains.compose.resources.stringResource
import trapgradienmobile.composeapp.generated.resources.Res
import trapgradienmobile.composeapp.generated.resources.ionizer_label
import trapgradienmobile.composeapp.generated.resources.ionizer_mode_title
import trapgradienmobile.composeapp.generated.resources.mode_auto
import trapgradienmobile.composeapp.generated.resources.mode_boost

@Composable
fun IonizerControlCard(
    isIonizerOn: Boolean,
    selectedMode: String,
    onIonizerToggle: (Boolean) -> Unit,
    onModeSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .heightIn(min = 150.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Icon(
                        imageVector = Icons.Default.BlurOn,
                        contentDescription = stringResource(Res.string.ionizer_label),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(Res.string.ionizer_mode_title),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        ),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Switch(
                    checked = isIonizerOn,
                    onCheckedChange = onIonizerToggle,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                        checkedTrackColor = MaterialTheme.colorScheme.primary
                    )
                )
            }

            // Mode Selection Chips Row
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(
                    "Auto" to Res.string.mode_auto,
                    "Boost" to Res.string.mode_boost
                ).forEach { (mode, res) ->
                    val isSelected = mode.equals(selectedMode, ignoreCase = true)
                    Surface(
                        onClick = { onModeSelect(mode) },
                        shape = RoundedCornerShape(6.dp),
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.primaryContainer
                        } else {
                            MaterialTheme.colorScheme.surfaceContainer
                        }
                    ) {
                        Text(
                            text = stringResource(res),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            ),
                            color = if (isSelected) {
                                MaterialTheme.colorScheme.onPrimaryContainer
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                }
            }
        }
    }
}
