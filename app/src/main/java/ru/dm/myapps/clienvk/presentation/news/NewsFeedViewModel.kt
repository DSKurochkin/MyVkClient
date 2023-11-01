package ru.dm.myapps.clienvk.presentation.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import ru.dm.myapps.clienvk.data.repository.NewsFeedRepository
import ru.dm.myapps.clienvk.domain.FeedPost

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NewsFeedRepository(application)
    private val initialState = NewsFeedScreenState.Initial
    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState>
        get() = _screenState

    init {
        _screenState.value = NewsFeedScreenState.Loading
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            repository.newsFlowLoader
                .filter { it.isNotEmpty() }
                .collect {
                    _screenState.value = NewsFeedScreenState.Posts(it)
                }
        }
    }

    fun loadNextNews() {
        _screenState.value = NewsFeedScreenState.Posts(repository.posts, true)
        viewModelScope.launch {
            repository.loadNextData()
        }
    }

    fun changeLikeStatus(post: FeedPost) {
        viewModelScope.launch {
            repository.changeLikeStatus(post)
            _screenState.value = NewsFeedScreenState.Posts(repository.posts)
        }
    }

    fun deletePost(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.ignorePost(feedPost)
            _screenState.value = NewsFeedScreenState.Posts(repository.posts)
        }
    }
}