package id.my.gradien.cloud.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import trapgradienmobile.composeapp.generated.resources.Res
import trapgradienmobile.composeapp.generated.resources.*

val InterFontFamily @Composable get() = FontFamily(
    Font(Res.font.interthin, FontWeight.Thin),
    Font(Res.font.interthinitalic, FontWeight.Thin, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.interextralight, FontWeight.ExtraLight),
    Font(Res.font.interextralightitalic, FontWeight.ExtraLight, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.interlight, FontWeight.Light),
    Font(Res.font.interlightitalic, FontWeight.Light, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.interregular, FontWeight.Normal),
    Font(Res.font.interitalic, FontWeight.Normal, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.intermedium, FontWeight.Medium),
    Font(Res.font.intermediumitalic, FontWeight.Medium, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.intersemibold, FontWeight.SemiBold),
    Font(Res.font.intersemibolditalic, FontWeight.SemiBold, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.interbold, FontWeight.Bold),
    Font(Res.font.interbolditalic, FontWeight.Bold, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.interextrabold, FontWeight.ExtraBold),
    Font(Res.font.interextrabolditalic, FontWeight.ExtraBold, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.interblack, FontWeight.Black),
    Font(Res.font.interblackitalic, FontWeight.Black, androidx.compose.ui.text.font.FontStyle.Italic)
)

val JetBrainsMonoFontFamily @Composable get() = FontFamily(
    Font(Res.font.jetbrainsmonothin, FontWeight.Thin),
    Font(Res.font.jetbrainsmonothinitalic, FontWeight.Thin, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.jetbrainsmonoextralight, FontWeight.ExtraLight),
    Font(Res.font.jetbrainsmonoextralightitalic, FontWeight.ExtraLight, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.jetbrainsmonolight, FontWeight.Light),
    Font(Res.font.jetbrainsmonolightitalic, FontWeight.Light, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.jetbrainsmonoregular, FontWeight.Normal),
    Font(Res.font.jetbrainsmonoitalic, FontWeight.Normal, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.jetbrainsmonomedium, FontWeight.Medium),
    Font(Res.font.jetbrainsmonomediumitalic, FontWeight.Medium, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.jetbrainsmonosemibold, FontWeight.SemiBold),
    Font(Res.font.jetbrainsmonosemibolditalic, FontWeight.SemiBold, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.jetbrainsmonobold, FontWeight.Bold),
    Font(Res.font.jetbrainsmonobolditalic, FontWeight.Bold, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.jetbrainsmonoextrabold, FontWeight.ExtraBold),
    Font(Res.font.jetbrainsmonoextrabolditalic, FontWeight.ExtraBold, androidx.compose.ui.text.font.FontStyle.Italic)
)

@Composable
fun Typography(): Typography {
    val inter = InterFontFamily
    val jetbrains = JetBrainsMonoFontFamily

    return Typography(
        displayLarge = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            lineHeight = 56.sp,
            letterSpacing = (-0.02).sp
        ),
        displayMedium = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp
        ),
        labelLarge = TextStyle(
            fontFamily = jetbrains,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.05.sp
        ),
        labelMedium = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.1.sp
        )
    )
}
