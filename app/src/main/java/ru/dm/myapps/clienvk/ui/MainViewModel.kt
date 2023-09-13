package ru.dm.myapps.clienvk.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dm.myapps.clienvk.domain.Comment
import ru.dm.myapps.clienvk.domain.FeedPost
import ru.dm.myapps.clienvk.domain.StatisticItem
import ru.dm.myapps.clienvk.domain.StatisticType
import ru.dm.myapps.clienvk.utils.genComment
import ru.dm.myapps.clienvk.utils.genFeedPost

class MainViewModel : ViewModel() {
    val comments = mutableListOf<Comment>().apply {
        repeat(15) {
            add(genComment(it))
        }
    }

    val posts = mutableListOf<FeedPost>().apply {
        add(FeedPost())
        repeat(20) {
            add(genFeedPost(it))
        }
    }

    private val initialState = HomeScreenState.Posts(posts)

    var savedState: HomeScreenState? = initialState


    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState>
        get() = _screenState

    fun showComments(feedPost: FeedPost) {
        savedState = _screenState.value
        _screenState.value = HomeScreenState.Comments(feedPost, comments)
    }

    fun closeComments() {
        _screenState.value = savedState
    }


    fun updateFeedPost(updType: StatisticType, feedPostToChange: FeedPost) {
        val currentState = _screenState.value
        if (currentState !is HomeScreenState.Posts) return
        val modifyPosts = (currentState as HomeScreenState.Posts).posts.toMutableList()
        modifyPosts.replaceAll {
            if (it.id == feedPostToChange.id) {
                it.copy(statisticItems = copyStatistic(it, updType))
            } else {
                it
            }
        }
        (currentState as HomeScreenState.Posts).posts = modifyPosts
    }

    fun delete(feedPost: FeedPost) {
        val currentState = _screenState.value
        if (currentState !is HomeScreenState.Posts) return
        val modifyPosts = currentState.posts.toMutableList()
        modifyPosts.remove(feedPost)
        currentState.posts = modifyPosts


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