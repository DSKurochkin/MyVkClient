package ru.dm.myapps.clienvk.ui

import androidx.activity.compose.BackHandler
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
import ru.dm.myapps.clienvk.domain.FeedPost

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val state = viewModel.screenState.observeAsState(HomeScreenState.Initial())

    val currentState = state.value
    when (currentState) {
        is HomeScreenState.Posts -> Posts(
            viewModel = viewModel,
            posts = currentState.posts
        )

        is HomeScreenState.Comments -> {
            CommentsScreen(
                post = currentState.post,
                comments = currentState.comments,
                onBackPressedListener = { viewModel.closeComments() }
            )
            BackHandler {
                viewModel.closeComments()
            }
        }

        is HomeScreenState.Initial -> {}
    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Posts(
    viewModel: MainViewModel,
    posts: List<FeedPost>,
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
        items(items = posts, key = { it.id }) { feedPost ->
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
                        onCommentsItemClickListener = { viewModel.showComments(feedPost) },
                        onLikeItemClickListener = { viewModel.updateFeedPost(it, feedPost) }
                    )
                },
                directions = setOf(DismissDirection.EndToStart)
            )
        }


    }

}