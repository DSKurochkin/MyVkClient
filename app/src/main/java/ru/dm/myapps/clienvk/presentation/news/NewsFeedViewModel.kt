package ru.dm.myapps.clienvk.presentation.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.dm.myapps.clienvk.data.repository.NewsFeedRepository
import ru.dm.myapps.clienvk.domain.FeedPost
import ru.dm.myapps.clienvk.extensions.withFlow

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NewsFeedRepository(application)
    private val recommendationStateFlow = repository.newsFlowLoader

    private val loadNextDataFlow = MutableSharedFlow<NewsFeedScreenState>()

    val screenState = recommendationStateFlow
        .filter { it.isNotEmpty() }
        .map { NewsFeedScreenState.Posts(it) as NewsFeedScreenState }
        .onStart { emit(NewsFeedScreenState.Loading) }
        .withFlow(loadNextDataFlow)


    fun loadNextNews() {
        viewModelScope.launch {
            loadNextDataFlow.emit(
                NewsFeedScreenState.Posts(
                    posts = recommendationStateFlow.value,
                    isLoading = true
                )
            )
            repository.loadNextData()
        }
    }

    fun changeLikeStatus(post: FeedPost) {
        viewModelScope.launch {
            repository.changeLikeStatus(post)

        }
    }

    fun deletePost(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.ignorePost(feedPost)

        }
    }
}