package ru.dm.myapps.clienvk.data.repository

import android.app.Application
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import ru.dm.myapps.clienvk.data.mapper.NewsFeedMapper
import ru.dm.myapps.clienvk.data.model.newsfeed.NewsFeedResponseDto
import ru.dm.myapps.clienvk.data.network.ApiFactory
import ru.dm.myapps.clienvk.data.network.ApiService
import ru.dm.myapps.clienvk.domain.FeedPost
import ru.dm.myapps.clienvk.domain.StatisticItem
import ru.dm.myapps.clienvk.domain.StatisticType

class NewsFeedRepository(private val application: Application) {
    private val storage = VKPreferencesKeyValueStorage(application)
    private val token = VKAccessToken.restore(storage) ?: throw RuntimeException("Invalid token")

    private val api: ApiService = ApiFactory.apiService
    private val mapper = NewsFeedMapper()
    private var nextFrom: String? = null

    private val _posts = mutableListOf<FeedPost>()
    val posts: List<FeedPost>
        get() = _posts.toList()

    suspend fun loadNews() {
        val startFrom = nextFrom
        if (startFrom == null && posts.isNotEmpty()) return
        val response: NewsFeedResponseDto = if (startFrom == null) {
            api.loadNews(token.accessToken)
        } else {
            api.loadNews(token.accessToken, startFrom)
        }
        nextFrom = response.content.nextFrom
        _posts.addAll(mapper.responseToPosts(response))
    }

    suspend fun changeLikeStatus(post: FeedPost) {
        val response = if (!post.isLiked) {
            api.addLike(
                token = token.accessToken,
                ownerId = post.sourceId,
                itemId = post.id
            )
        } else {
            api.deleteLike(
                token = token.accessToken,
                ownerId = post.sourceId,
                itemId = post.id
            )
        }

        val newLikeCount = response.like.count

        val newStatistic = post.statisticItems.toMutableMap()
        newStatistic.put(StatisticType.LIKES, StatisticItem(StatisticType.LIKES, newLikeCount))
        val newPost = post.copy(statisticItems = newStatistic, isLiked = !post.isLiked)
        _posts[_posts.indexOf(post)] = newPost
    }
}