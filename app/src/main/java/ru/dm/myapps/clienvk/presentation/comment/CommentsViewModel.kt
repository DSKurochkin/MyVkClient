package ru.dm.myapps.clienvk.presentation.comment

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.dm.myapps.clienvk.domain.enity.FeedPost
import ru.dm.myapps.clienvk.domain.usecases.GetCommentsUseCase
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    private val post: FeedPost,
    getCommentsUseCase: GetCommentsUseCase
) : ViewModel() {

    val commentsScreenState: Flow<CommentsScreenState> =
        getCommentsUseCase(post)
            .map {
                CommentsScreenState.Comments(
                    post = post,
                    comments = it
                )
            }
}