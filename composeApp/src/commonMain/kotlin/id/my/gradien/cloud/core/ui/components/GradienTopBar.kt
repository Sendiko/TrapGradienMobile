package id.my.gradien.cloud.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import trapgradienmobile.composeapp.generated.resources.Res
import trapgradienmobile.composeapp.generated.resources.app_name
import trapgradienmobile.composeapp.generated.resources.trapgradien

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradienTopBar(
    onAlertClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        navigationIcon = {
            Image(
                painter = painterResource(Res.drawable.trapgradien),
                contentDescription = null,
                modifier = Modifier.padding(start = 16.dp).size(32.dp)
            )
        },
        title = {
            Text(
                text = stringResource(Res.string.app_name),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
        },
        actions = {
            IconButton(onClick = onAlertClick) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Alerts"
                )
            }
        },
        modifier = modifier
    )
}
