package ru.dm.myapps.clienvk.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dm.myapps.clienvk.domain.FeedPost
import ru.dm.myapps.clienvk.domain.StatisticItem
import ru.dm.myapps.clienvk.domain.StatisticType

class MainViewModel : ViewModel() {
    private val _feedPost = MutableLiveData<FeedPost>(FeedPost())
    val feedPost: LiveData<FeedPost>
        get() = _feedPost

    fun updateFeedPost(type: StatisticType) {
        val oldFeedPost = feedPost.value ?: throw IllegalArgumentException()
        val newStatistic = oldFeedPost.statisticItems.toMutableMap().apply {
            val item = this[type] ?: StatisticItem(type, 0)
            this.put(type, StatisticItem(type, item.count + 1))
        }
        _feedPost.value = _feedPost.value?.copy(statisticItems = newStatistic)
    }

}