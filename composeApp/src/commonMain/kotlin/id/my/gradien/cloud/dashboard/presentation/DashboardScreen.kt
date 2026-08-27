package id.my.gradien.cloud.dashboard.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.my.gradien.cloud.core.ui.theme.AppTheme
import id.my.gradien.cloud.dashboard.presentation.components.DashboardCard
import id.my.gradien.cloud.dashboard.presentation.components.LocationSection
import id.my.gradien.cloud.dashboard.presentation.components.SectionHeader
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import trapgradienmobile.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    state: DashboardState,
    onEvent: (DashboardEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Text(
                text = stringResource(Res.string.hello_user, state.userName),
                fontSize = 28.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            LocationSection(
                location = state.location,
                onSwapClick = { onEvent(DashboardEvent.OnSwapLocationClicked) }
            )

            Spacer(modifier = Modifier.height(32.dp))

            SectionHeader(
                title = stringResource(Res.string.alat_title),
                actionText = stringResource(Res.string.lihat_semua),
                onActionClick = { onEvent(DashboardEvent.OnLihatSemuaAlatClicked) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.height(220.dp)
            ) {
                items(state.alatList) { alatName ->
                    DashboardCard(
                        title = alatName,
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        iconRes = Res.drawable.ic_chip
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(Res.string.fitur_title),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            val fiturList = listOf(
                Pair(stringResource(Res.string.lihat_semua_alat), Res.drawable.ic_chip),
                Pair(stringResource(Res.string.monitor_kualitas), Res.drawable.ic_wave),
                Pair(stringResource(Res.string.informasi_polusi), Res.drawable.ic_info_circle),
                Pair(stringResource(Res.string.tambah_alat), Res.drawable.ic_add_chip)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.height(220.dp)
            ) {
                items(fiturList) { fitur ->
                    DashboardCard(
                        title = fitur.first,
                        backgroundColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        iconRes = fitur.second
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(Res.string.version_format, state.version),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }

    if (state.isLocationPickerOpen) {
        ModalBottomSheet(
            onDismissRequest = { onEvent(DashboardEvent.OnDismissBottomSheet) },
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                Text(
                    text = stringResource(Res.string.current_location_label),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                LazyColumn {
                    items(state.availableLocations) { location ->
                        ListItem(
                            headlineContent = { Text(location) },
                            trailingContent = {
                                if (location == state.location) {
                                    Icon(
                                        painter = painterResource(Res.drawable.ic_check),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            },
                            modifier = Modifier.clickable {
                                onEvent(DashboardEvent.OnLocationChanged(location))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DashboardScreenPreview() {
    AppTheme {
        DashboardScreen(
            state = DashboardState(),
            onEvent = {}
        )
    }
}
