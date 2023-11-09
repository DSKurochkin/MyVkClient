package ru.dm.myapps.clienvk.presentation.comment

import android.app.Application
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.dm.myapps.clienvk.data.repository.NewsFeedRepositoryImpl
import ru.dm.myapps.clienvk.domain.enity.FeedPost
import ru.dm.myapps.clienvk.domain.usecases.GetCommentsUseCase

class CommentsViewModel(
    post: FeedPost,
    application: Application
) : ViewModel() {
    private val repository = NewsFeedRepositoryImpl(application)
    private val getCommentsUseCase = GetCommentsUseCase(repository)
    val commentsScreenState: Flow<CommentsScreenState> =
        getCommentsUseCase(post)
            .map {
                CommentsScreenState.Comments(
                    post = post,
                    comments = it
                )
            }
}