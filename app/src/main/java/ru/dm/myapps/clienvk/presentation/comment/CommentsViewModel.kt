package ru.dm.myapps.clienvk.presentation.comment

import android.app.Application
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.dm.myapps.clienvk.data.repository.NewsFeedRepository
import ru.dm.myapps.clienvk.domain.FeedPost

class CommentsViewModel(
    post: FeedPost,
    application: Application
) : ViewModel() {
    private val repository = NewsFeedRepository(application)
    val commentsScreenState: Flow<CommentsScreenState> =
        repository.getComments(post)
            .map {
                CommentsScreenState.Comments(
                    post = post,
                    comments = it
                )
            }
}