package id.my.gradien.cloud.nodes.list.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import trapgradienmobile.composeapp.generated.resources.Res
import trapgradienmobile.composeapp.generated.resources.alerts_description
import trapgradienmobile.composeapp.generated.resources.filter_all_nodes
import trapgradienmobile.composeapp.generated.resources.status_offline
import trapgradienmobile.composeapp.generated.resources.status_online
import id.my.gradien.cloud.nodes.list.presentation.models.NodeFilter

@Composable
fun NodeFilterRow(
    selectedFilter: NodeFilter,
    onFilterSelect: (NodeFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        items(NodeFilter.entries) { filter ->
            val isSelected = filter == selectedFilter
            val (dotColor, chipText) = when (filter) {
                NodeFilter.ALL -> MaterialTheme.colorScheme.primary to stringResource(Res.string.filter_all_nodes)
                NodeFilter.ONLINE -> MaterialTheme.colorScheme.secondary to stringResource(Res.string.status_online)
                NodeFilter.OFFLINE -> MaterialTheme.colorScheme.outline to stringResource(Res.string.status_offline)
                NodeFilter.ALERTS -> MaterialTheme.colorScheme.error to stringResource(Res.string.alerts_description)
            }

            Surface(
                onClick = { onFilterSelect(filter) },
                shape = CircleShape,
                color = if (isSelected) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.surfaceContainer
                },
                border = if (isSelected) null else {
                    BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                },
                tonalElevation = if (isSelected) 2.dp else 0.dp
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else dotColor)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = chipText,
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        ),
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        }
                    )
                }
            }
        }
    }
}
