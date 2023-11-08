package ru.dm.myapps.clienvk.presentation.comment

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import ru.dm.myapps.clienvk.domain.Comment
import ru.dm.myapps.clienvk.domain.FeedPost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable


fun CommentsScreen(
    onBackPressedListener: () -> Unit,
    post: FeedPost
) {
    val viewModel: CommentsViewModel = viewModel(
        factory = CommentsViewModelFactory(
            post,
            LocalContext.current.applicationContext as Application
        )
    )
    val screenState = viewModel.commentsScreenState.collectAsState(CommentsScreenState.Initial)
    val currentState = screenState.value
    if (currentState !is CommentsScreenState.Comments) return
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .wrapContentHeight(),
                title = {
                    Text(
                        text = "Comment for post by community: ${currentState.post.communityName}",
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.clickable { onBackPressedListener() }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        contentColor = contentColorFor(MaterialTheme.colorScheme.primary)
    )
    { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .background(color = MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 72.dp
            ),
        )
        {
            items(currentState.comments, { it.id }) { comment ->
                Comment(comment = comment)
            }

        }
    }
}

@Composable
private fun Comment(comment: Comment) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)

    ) {
        AsyncImage(
            model = comment.authorAvatarUrl,
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop

        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Row {
                Text(
                    text = comment.authorName,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(4.dp))
                //Text(text = "CommentId: ${comment.id}", fontSize = 15.sp, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = comment.text, fontSize = 14.sp, color = MaterialTheme.colorScheme.onPrimary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = comment.publicationDate,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }


}
