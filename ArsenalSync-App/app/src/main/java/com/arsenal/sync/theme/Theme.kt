package com.arsenal.sync.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.arsenal.sync.core.domain.utils.ThemeOption

private val DarkColorScheme = darkColorScheme(
    primary = lightYellow,
    secondary = lightYellow,
    tertiary = lightGrey,
    background = black,
    surface = black,
    onPrimary = white,
    onSecondary = white,
    onTertiary = darkGrey,
    onBackground = white,
    onSurface = white
)

private val LightColorScheme = lightColorScheme(
    primary = darkYellow,
    secondary = darkYellow,
    tertiary = darkGrey,
    background = white,
    surface = white,
    onPrimary = black,
    onSecondary = black,
    onTertiary = lightGrey,
    onBackground = black,
    onSurface = black
)

@Composable
fun ArsenalSyncTheme(
    themeOption: ThemeOption,
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeOption) {
        ThemeOption.LIGHT -> LightColorScheme
        ThemeOption.DARK -> DarkColorScheme
        ThemeOption.SYSTEM -> {
            if (isSystemInDarkTheme()) DarkColorScheme
            else LightColorScheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}