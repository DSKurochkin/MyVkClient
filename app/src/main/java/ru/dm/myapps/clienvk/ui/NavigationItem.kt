package ru.dm.myapps.clienvk.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import ru.dm.myapps.clienvk.R

sealed class NavigationItem(
    val icon: ImageVector,
    val labelResId: Int
) {
    object Home : NavigationItem(
        icon = Icons.Outlined.Home,
        labelResId = R.string.icon_nav_home
    )

    object Favorite : NavigationItem(
        icon = Icons.Outlined.Favorite,
        labelResId = R.string.icon_nav_favorite
    )

    object Profile : NavigationItem(
        icon = Icons.Outlined.Person,
        labelResId = R.string.icon_nav_profile
    )
}




