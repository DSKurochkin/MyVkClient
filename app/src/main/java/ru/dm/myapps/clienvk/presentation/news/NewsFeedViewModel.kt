package ru.dm.myapps.clienvk.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dm.myapps.clienvk.domain.FeedPost
import ru.dm.myapps.clienvk.domain.StatisticItem
import ru.dm.myapps.clienvk.domain.StatisticType
import ru.dm.myapps.clienvk.utils.genFeedPost

class NewsFeedViewModel : ViewModel() {
    val posts = mutableListOf<FeedPost>().apply {
        add(FeedPost())
        repeat(20) {
            add(genFeedPost(it))
        }
    }

    private val initialState = NewsFeedScreenState.Posts(posts)

    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState>
        get() = _screenState

    fun updateFeedPost(updType: StatisticType, feedPostToChange: FeedPost) {
        val currentState = _screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return
        val modifyPosts = (currentState as NewsFeedScreenState.Posts).posts.toMutableList()
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