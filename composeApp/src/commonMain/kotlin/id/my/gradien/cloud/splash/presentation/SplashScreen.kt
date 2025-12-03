package id.my.gradien.cloud.splash.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import trapgradienmobile.composeapp.generated.resources.Res
import trapgradienmobile.composeapp.generated.resources.app_name
import trapgradienmobile.composeapp.generated.resources.trapgradien

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.tertiaryContainer,
                        MaterialTheme.colorScheme.primaryContainer
                    ),
                    start = androidx.compose.ui.geometry.Offset(0f, Float.POSITIVE_INFINITY),
                    end = androidx.compose.ui.geometry.Offset(Float.POSITIVE_INFINITY, 0f)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.trapgradien),
            contentDescription = stringResource(Res.string.app_name),
            modifier = Modifier.size(156.dp)
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}