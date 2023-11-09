package ru.dm.myapps.clienvk.data.repository

import android.app.Application
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import ru.dm.myapps.clienvk.data.mapper.CommentsMapper
import ru.dm.myapps.clienvk.data.mapper.NewsFeedMapper
import ru.dm.myapps.clienvk.data.model.newsfeed.NewsFeedResponseDto
import ru.dm.myapps.clienvk.data.network.ApiFactory
import ru.dm.myapps.clienvk.data.network.ApiService
import ru.dm.myapps.clienvk.domain.AuthState
import ru.dm.myapps.clienvk.domain.NewsFeedRepository
import ru.dm.myapps.clienvk.domain.enity.Comment
import ru.dm.myapps.clienvk.domain.enity.FeedPost
import ru.dm.myapps.clienvk.domain.enity.StatisticItem
import ru.dm.myapps.clienvk.domain.enity.StatisticType
import ru.dm.myapps.clienvk.extensions.withFlow

class NewsFeedRepositoryImpl(application: Application) : NewsFeedRepository {
    private val api: ApiService = ApiFactory.apiService
    private val postMapper = NewsFeedMapper()
    private val commentMapper = CommentsMapper()
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private var nextFrom: String? = null

    //Authorization
    private val storage = VKPreferencesKeyValueStorage(application)
    private val token
        get() = VKAccessToken.restore(storage)
    private val checkAuthEvents = MutableSharedFlow<Unit>(1)
    private val authStateFlow = flow {
        checkAuthEvents.emit(Unit)
        checkAuthEvents.collect {
            val currentToken = token
            val loggedIn = currentToken != null && currentToken.isValid
            val authState = if (loggedIn) AuthState.Authorized else AuthState.NotAuthorized
            emit(authState)
        }
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = AuthState.Initial
    )


    //Cached newsList
    private val _posts = mutableListOf<FeedPost>()
    private val posts: List<FeedPost>
        get() = _posts.toList()


    private val loadedListFlow = flow {
        nextDataNeededEvents.emit(Unit)
        nextDataNeededEvents.collect {
            val startFrom = nextFrom
            if (startFrom == null && posts.isNotEmpty()) {
                emit(posts)
                return@collect
            }

            val response: NewsFeedResponseDto = if (startFrom == null) {
                api.loadNews(getAccessToken())
            } else {
                //for visible progress in testing
                delay(2000)
                //
                api.loadNews(getAccessToken(), startFrom)
            }
            nextFrom = response.content.nextFrom
            _posts.addAll(postMapper.responseToPosts(response))
            emit(posts)
        }
    }
        .retry {
            delay(RETR_TIMEOUT_MILLS)
            true
        }

    private val nextDataNeededEvents = MutableSharedFlow<Unit>(replay = 1)
    private val refreshListFlow = MutableSharedFlow<List<FeedPost>>()


    override fun getNewsLoader(): StateFlow<List<FeedPost>> = loadedListFlow
        .withFlow(refreshListFlow)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Lazily,
            initialValue = posts
        )


    override fun getAuthStateFlow(): Flow<AuthState> = authStateFlow


    override suspend fun changeLikeStatus(post: FeedPost) {
        val response = if (!post.isLiked) {
            api.addLike(
                token = getAccessToken(),
                ownerId = post.sourceId,
                itemId = post.id
            )
        } else {
            api.deleteLike(
                token = getAccessToken(),
                ownerId = post.sourceId,
                itemId = post.id
            )
        }

        val newLikeCount = response.like.count

        val newStatistic = post.statisticItems.toMutableMap()
        newStatistic.put(StatisticType.LIKES, StatisticItem(StatisticType.LIKES, newLikeCount))
        val newPost = post.copy(statisticItems = newStatistic, isLiked = !post.isLiked)
        _posts[_posts.indexOf(post)] = newPost
        refreshListFlow.emit(posts)
    }

    override suspend fun ignorePost(post: FeedPost) {
        _posts.remove(post)
        api.ignorePost(getAccessToken(), post.sourceId, post.id)
        refreshListFlow.emit(posts)
    }

    override suspend fun loadNextData() {
        nextDataNeededEvents.emit(Unit)
    }

    override fun getComments(post: FeedPost): Flow<List<Comment>> = flow {
        val response = api.getComments(
            getAccessToken(),
            post.sourceId,
            post.id
        )
        emit(commentMapper.responseToComments(response))
    }.retry {
        delay(RETR_TIMEOUT_MILLS)
        true
    }

    override suspend fun checkAuth() {
        checkAuthEvents.emit(Unit)
    }

    private fun getAccessToken(): String {
        return token?.accessToken ?: throw IllegalStateException("Token is null")
    }

    companion object {
        const val RETR_TIMEOUT_MILLS = 2000L
    }

}
