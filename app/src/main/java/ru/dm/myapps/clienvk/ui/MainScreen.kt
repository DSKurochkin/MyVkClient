package ru.dm.myapps.clienvk.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import ru.dm.myapps.clienvk.ui.theme.ClienVKTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val snakeBarHostState = SnackbarHostState()
    val fabVisible = remember { mutableStateOf(true) }


    ModalNavigationDrawer(
        drawerContent = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null)
            }
        }
    ) {
        Scaffold(
            topBar = { TopBar() },
            bottomBar = { BottomBar() },
            floatingActionButton = {
                if (fabVisible.value) {
                    val scope = rememberCoroutineScope()
                    FloatingActionButton(
                        onClick = {
                            scope.launch {
                                val action = snakeBarHostState.showSnackbar(
                                    message = "Im Snackbar",
                                    actionLabel = "Hide",
                                    duration = SnackbarDuration.Short
                                )
                                if (action == SnackbarResult.ActionPerformed) {
                                    fabVisible.value = false
                                }

                            }

                        })
                    {
                        Icon(imageVector = Icons.Outlined.Favorite, contentDescription = null)
                    }
                }

            },
            snackbarHost = { SnackbarHost(snakeBarHostState) }

        ) {
            it
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    TopAppBar(
        title = {
            Text(text = "TopAppBar")
        },
        navigationIcon = {
            IconButton(
                onClick = {},
            )
            {
                Icon(Icons.Filled.Menu, null)
            }
        },
    )

}


@Composable
private fun BottomBar() {
    val selectedPosition = remember {
        mutableStateOf(0)
    }

    val items = listOf(NavigationItem.Home, NavigationItem.Favorite, NavigationItem.Profile)
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.onBackground
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedPosition.value,
                onClick = {
                    selectedPosition.value = index
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


@Preview
@Composable
private fun TestLightTheme() {
    ClienVKTheme(darkTheme = false) {
        MainScreen()
    }
}

@Preview
@Composable
private fun TestDarkTheme() {
    ClienVKTheme(darkTheme = true) {
        MainScreen()
    }
}