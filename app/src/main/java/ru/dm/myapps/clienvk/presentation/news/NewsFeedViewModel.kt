package ru.dm.myapps.clienvk.presentation.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.launch
import ru.dm.myapps.clienvk.data.mapper.NewsFeedMapper
import ru.dm.myapps.clienvk.data.network.ApiFactory
import ru.dm.myapps.clienvk.domain.FeedPost
import ru.dm.myapps.clienvk.domain.StatisticItem
import ru.dm.myapps.clienvk.domain.StatisticType

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {
    private val api = ApiFactory.apiService
    private val mapper = NewsFeedMapper()
    private val initialState = NewsFeedScreenState.Initial

    init {
        loadNews()
    }


    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState>
        get() = _screenState

    private fun loadNews() {
        viewModelScope.launch {
            val storage = VKPreferencesKeyValueStorage(getApplication())
            val token = VKAccessToken.restore(storage) ?: return@launch
            val posts = mapper.responseToPosts(api.loadNews(token.accessToken))
            _screenState.value = NewsFeedScreenState.Posts(posts)
        }
    }

    fun updateFeedPost(updType: StatisticType, feedPostToChange: FeedPost) {
        val currentState = _screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return
        val modifyPosts = (currentState).posts.toMutableList()
        modifyPosts.replaceAll {
            if (it.id == feedPostToChange.id) {
                it.copy(statisticItems = copyStatistic(it, updType))
            } else {
                it
            }
        }
        _screenState.value = NewsFeedScreenState.Posts(modifyPosts)
    }

    fun delete(feedPost: FeedPost) {
        val currentState = _screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return
        val modifyPosts = currentState.posts.toMutableList()
        modifyPosts.remove(feedPost)
        _screenState.value = NewsFeedScreenState.Posts(modifyPosts)

    }

    private fun copyStatistic(
        feedPost: FeedPost,
        updType: StatisticType
    ): Map<StatisticType, StatisticItem> {
        return feedPost.statisticItems.toMutableMap().apply {
            val item = this[updType] ?: StatisticItem(updType, 0)
            this.put(updType, StatisticItem(updType, item.count + 1))
        }
    }
}