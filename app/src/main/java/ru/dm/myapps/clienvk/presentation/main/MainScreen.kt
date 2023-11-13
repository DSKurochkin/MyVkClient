package ru.dm.myapps.clienvk.presentation.main

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
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
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.dm.myapps.clienvk.navigation.AppNavGraph
import ru.dm.myapps.clienvk.navigation.NavigationState
import ru.dm.myapps.clienvk.navigation.rememberNavigationState
import ru.dm.myapps.clienvk.presentation.ViewModelFactory
import ru.dm.myapps.clienvk.presentation.comment.CommentsScreen
import ru.dm.myapps.clienvk.presentation.news.NewsFeed
import ru.dm.myapps.clienvk.presentation.un_use.FavoriteScreen
import ru.dm.myapps.clienvk.presentation.un_use.ProfileScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(viewModelFactory: ViewModelFactory) {
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = { BottomBar(navigationState) },

        ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            newsFeedScreenCallback = {
                NewsFeed(
                    viewModelFactory,
                    onCommentsItemClickListener = { post ->
                        navigationState.navigateToComment(post)
                    },
                    paddingValues
                )
            },
            favoriteScreensCallback = { FavoriteScreen() },
            profileScreenCallback = { ProfileScreen() },
            commentsScreenContentCallback = { post ->
                CommentsScreen(
                    onBackPressedListener = { navigationState.navHostController.popBackStack() },
                    post = post
                )
                BackHandler {
                    navigationState.navHostController.popBackStack()
                }
            }
        )
    }
}


@Composable
private fun BottomBar(navigationState: NavigationState) {
    val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

    val items = listOf(NavigationItem.Home, NavigationItem.Favorite, NavigationItem.Profile)
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.onBackground
    ) {
        items.forEach { item ->
            val selected = navBackStackEntry?.destination?.hierarchy?.any {
                it.route == item.screenName.route
            } ?: false
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navigationState.navigateTo(item.screenName.route)
                    }
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
