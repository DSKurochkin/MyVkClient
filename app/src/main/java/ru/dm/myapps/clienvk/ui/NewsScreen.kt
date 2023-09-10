package ru.dm.myapps.clienvk.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NewsScreen(viewModel: MainViewModel) {
    val feedPostsListState = viewModel.feedPostList.observeAsState(listOf())
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