package ru.dm.myapps.clienvk.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dm.myapps.clienvk.domain.FeedPost
import ru.dm.myapps.clienvk.domain.StatisticItem
import ru.dm.myapps.clienvk.domain.StatisticType

class MainViewModel : ViewModel() {
    private val _feedPostList = MutableLiveData<List<FeedPost>>(
        mutableListOf<FeedPost>().apply {
            add(FeedPost())
            repeat(20) {
                add(FeedPost.genFeedPost(it))
            }
        }
    )
    val feedPostList: LiveData<List<FeedPost>>
        get() = _feedPostList


    fun updateFeedPost(updType: StatisticType, feedPostToChange: FeedPost) {
        val modifyPosts = _feedPostList.value?.toMutableList() ?: mutableListOf()
        modifyPosts.replaceAll {
            if (it.id == feedPostToChange.id) {
                it.copy(statisticItems = copyStatistic(it, updType))
            } else {
                it
            }
        }
        _feedPostList.value = modifyPosts
    }

    fun delete(feedPost: FeedPost) {
        val modifyPosts = _feedPostList.value?.toMutableList() ?: mutableListOf()
        modifyPosts.remove(feedPost)
        _feedPostList.value = modifyPosts

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