package ru.dm.myapps.clienvk.data.repository

import android.app.Application
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import ru.dm.myapps.clienvk.data.mapper.CommentsMapper
import ru.dm.myapps.clienvk.data.mapper.NewsFeedMapper
import ru.dm.myapps.clienvk.data.model.comments.CommentsResponse
import ru.dm.myapps.clienvk.data.model.newsfeed.NewsFeedResponseDto
import ru.dm.myapps.clienvk.data.network.ApiFactory
import ru.dm.myapps.clienvk.data.network.ApiService
import ru.dm.myapps.clienvk.domain.Comment
import ru.dm.myapps.clienvk.domain.FeedPost
import ru.dm.myapps.clienvk.domain.StatisticItem
import ru.dm.myapps.clienvk.domain.StatisticType

class NewsFeedRepository(application: Application) {
    private val storage = VKPreferencesKeyValueStorage(application)
    private val token = VKAccessToken.restore(storage) ?: throw RuntimeException("Invalid token")

    private val api: ApiService = ApiFactory.apiService
    private val postMapper = NewsFeedMapper()
    private val commentMapper = CommentsMapper()
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
        _posts.addAll(postMapper.responseToPosts(response))
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

    suspend fun ignorePost(post: FeedPost) {
        _posts.remove(post)
        api.ignorePost(token.accessToken, post.sourceId, post.id)
    }

    private suspend fun getCommentsResponse(post: FeedPost): CommentsResponse {
        return api.getComments(
            token.accessToken,
            post.sourceId,
            post.id
        )
    }

    suspend fun getComments(post: FeedPost): List<Comment> {
        return commentMapper.responseToComments(
            api.getComments(
                token.accessToken,
                post.sourceId,
                post.id
            )
        )
    }
}