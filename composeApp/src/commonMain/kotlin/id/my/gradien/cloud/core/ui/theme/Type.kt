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

val MontserratFontFamily @Composable get() = FontFamily(
    Font(Res.font.montserrat_thin, FontWeight.Thin),
    Font(Res.font.montserrat_thinitalic, FontWeight.Thin, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.montserrat_extralight, FontWeight.ExtraLight),
    Font(Res.font.montserrat_extralightitalic, FontWeight.ExtraLight, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.montserrat_light, FontWeight.Light),
    Font(Res.font.montserrat_lightitalic, FontWeight.Light, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.montserrat_regular, FontWeight.Normal),
    Font(Res.font.montserrat_italic, FontWeight.Normal, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.montserrat_medium, FontWeight.Medium),
    Font(Res.font.montserrat_mediumitalic, FontWeight.Medium, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.montserrat_semibold, FontWeight.SemiBold),
    Font(Res.font.montserrat_semibolditalic, FontWeight.SemiBold, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.montserrat_bold, FontWeight.Bold),
    Font(Res.font.montserrat_bolditalic, FontWeight.Bold, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.montserrat_extrabold, FontWeight.ExtraBold),
    Font(Res.font.montserrat_extrabolditalic, FontWeight.ExtraBold, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.montserrat_black, FontWeight.Black),
    Font(Res.font.montserrat_blackitalic, FontWeight.Black, androidx.compose.ui.text.font.FontStyle.Italic)
)

val RobotoFontFamily @Composable get() = FontFamily(
    Font(Res.font.roboto_thin, FontWeight.Thin),
    Font(Res.font.roboto_thinitalic, FontWeight.Thin, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.roboto_extralight, FontWeight.ExtraLight),
    Font(Res.font.roboto_extralightitalic, FontWeight.ExtraLight, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.roboto_light, FontWeight.Light),
    Font(Res.font.roboto_lightitalic, FontWeight.Light, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.roboto_regular, FontWeight.Normal),
    Font(Res.font.roboto_italic, FontWeight.Normal, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.roboto_medium, FontWeight.Medium),
    Font(Res.font.roboto_mediumitalic, FontWeight.Medium, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.roboto_semibold, FontWeight.SemiBold),
    Font(Res.font.roboto_semibolditalic, FontWeight.SemiBold, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.roboto_bold, FontWeight.Bold),
    Font(Res.font.roboto_bolditalic, FontWeight.Bold, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.roboto_extrabold, FontWeight.ExtraBold),
    Font(Res.font.roboto_extrabolditalic, FontWeight.ExtraBold, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(Res.font.roboto_black, FontWeight.Black),
    Font(Res.font.roboto_blackitalic, FontWeight.Black, androidx.compose.ui.text.font.FontStyle.Italic)
)

@Composable
fun Typography(): Typography {
    val montserrat = MontserratFontFamily
    val roboto = RobotoFontFamily

    return Typography(
        displayLarge = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = (-0.25).sp
        ),
        displayMedium = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp
        ),
        displaySmall = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        ),
        titleLarge = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        titleSmall = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        ),
        labelLarge = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelMedium = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
    )
}
