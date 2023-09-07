package ru.dm.myapps.clienvk.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val feedPostsListState = viewModel.feedPostList.observeAsState(listOf())

    Scaffold(
        bottomBar = { BottomBar() },
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 72.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = feedPostsListState.value, key = { it.id }) { feedPost ->
                val dismissState = rememberDismissState(positionalThreshold = { 100.dp.toPx() })
                if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                    viewModel.delete(feedPost)
                }
                SwipeToDismiss(
                    modifier = Modifier.animateItemPlacement(),
                    state = dismissState,
                    background = { },
                    dismissContent = {
                        PostCard(
                            modifier = Modifier.padding(10.dp),
                            feedPost = feedPost,
                            onViewsItemClickListener = { viewModel.updateFeedPost(it, feedPost) },
                            onSharedItemClickListener = { viewModel.updateFeedPost(it, feedPost) },
                            onCommentsItemClickListener = {
                                viewModel.updateFeedPost(
                                    it,
                                    feedPost
                                )
                            },
                            onLikeItemClickListener = { viewModel.updateFeedPost(it, feedPost) }
                        )
                    },
                    directions = setOf(DismissDirection.EndToStart)
                )
            }

        }
    }
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