package ru.dm.myapps.clienvk.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dm.myapps.clienvk.domain.FeedPost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val feedPost = viewModel.feedPost.observeAsState(FeedPost())
    Scaffold(
        bottomBar = { BottomBar() },

        ) {
        PostCard(
            modifier = Modifier.padding(10.dp),
            feedPost = feedPost.value,
            onViewsItemClickListener = { viewModel.updateFeedPost(it) },
            onSharedItemClickListener = { viewModel.updateFeedPost(it) },
            onCommentsItemClickListener = { viewModel.updateFeedPost(it) },
            onLikeItemClickListener = { viewModel.updateFeedPost(it) }
        )
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