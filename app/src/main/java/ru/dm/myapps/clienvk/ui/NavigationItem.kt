package ru.dm.myapps.clienvk.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import ru.dm.myapps.clienvk.R
import ru.dm.myapps.clienvk.navigation.Screen

sealed class NavigationItem(
    val icon: ImageVector,
    val labelResId: Int,
    val screenName: Screen
) {
    object Home : NavigationItem(
        icon = Icons.Outlined.Home,
        labelResId = R.string.icon_nav_home,
        screenName = Screen.Home
    )

    object Favorite : NavigationItem(
        icon = Icons.Outlined.Favorite,
        labelResId = R.string.icon_nav_favorite,
        screenName = Screen.Favorite
    )

    object Profile : NavigationItem(
        icon = Icons.Outlined.Person,
        labelResId = R.string.icon_nav_profile,
        screenName = Screen.PROFILE
    )
}




