package ru.dm.myapps.clienvk.presentation.comment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.dm.myapps.clienvk.domain.FeedPost

class CommentsViewModelFactory(
    private val post: FeedPost,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(post, application) as T
    }
}