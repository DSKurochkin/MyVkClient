package ru.dm.myapps.clienvk.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.dm.myapps.clienvk.navigation.AppNavGraph
import ru.dm.myapps.clienvk.navigation.NavigationState
import ru.dm.myapps.clienvk.navigation.rememberNavigationState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navigationState = rememberNavigationState()
    Scaffold(
        bottomBar = { BottomBar(navigationState) },

        ) {
        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreenshotCallback = {
                HomeScreen(viewModel = viewModel)
            },
            favoriteScreenshotCallback = { FavoriteScreen() },
            profileScreenshotCallback = { ProfileScreen() },
        )
    }
}


@Composable
private fun BottomBar(navigationState: NavigationState) {
    val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
    val currentRout = navBackStackEntry?.destination?.route

    val items = listOf(NavigationItem.Home, NavigationItem.Favorite, NavigationItem.Profile)
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.onBackground
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.screenName.route == currentRout,
                onClick = {
                    navigationState.navigateTo(item.screenName.route)
                },
                icon = { Icon(item.icon, null) },
                label = { Text(stringResource(id = item.labelResId)) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSecondary
                )
            )
        }
    }
}


//@Preview
//@Composable
//private fun TestLightTheme() {
//    ClienVKTheme(darkTheme = false) {
//        MainScreen()
//    }
//}
//
//@Preview
//@Composable
//private fun TestDarkTheme() {
//    ClienVKTheme(darkTheme = true) {
//        MainScreen()
//    }
//}