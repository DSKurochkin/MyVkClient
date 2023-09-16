package ru.dm.myapps.clienvk.ui.comment_scr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.dm.myapps.clienvk.domain.FeedPost

class CommentsViewModelFactory(private val post: FeedPost) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(post) as T
    }
}