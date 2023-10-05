package ru.dm.myapps.clienvk.presentation.comment

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.dm.myapps.clienvk.data.repository.NewsFeedRepository
import ru.dm.myapps.clienvk.domain.FeedPost

class CommentsViewModel(
    post: FeedPost,
    application: Application
) : ViewModel() {
    private val repository = NewsFeedRepository(application)
    private var _commentsScreenState =
        MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val commentsScreenState: LiveData<CommentsScreenState>
        get() = _commentsScreenState

    private fun loadComments(post: FeedPost) {
        viewModelScope.launch {
            val comments = repository.getComments(post)
            _commentsScreenState.value = CommentsScreenState.Comments(post, comments)
        }
    }

    init {
        loadComments(post)
    }
}