package ru.dm.myapps.clienvk.ui.comment_scr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dm.myapps.clienvk.domain.Comment
import ru.dm.myapps.clienvk.domain.FeedPost
import ru.dm.myapps.clienvk.ui.state.CommentsScreenState
import ru.dm.myapps.clienvk.utils.genComment

class CommentsViewModel(
    post: FeedPost
) : ViewModel() {
    private var _commentsScreenState =
        MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val commentsScreenState: LiveData<CommentsScreenState>
        get() = _commentsScreenState

    fun loadComments(post: FeedPost) {
        val comments = mutableListOf<Comment>().apply {
            repeat(15) {
                add(genComment(it))
            }
        }
        _commentsScreenState.value = CommentsScreenState.Comments(post, comments)
    }

    init {
        loadComments(post)
    }

//    fun loadComments(post: FeedPost, comments:List<Comment>){
//        _commentsScreenState.value = CommentsScreenState.Comments(post, comments)
//    }
}