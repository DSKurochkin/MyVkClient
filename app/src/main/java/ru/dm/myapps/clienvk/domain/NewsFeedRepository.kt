package ru.dm.myapps.clienvk.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.dm.myapps.clienvk.domain.enity.Comment
import ru.dm.myapps.clienvk.domain.enity.FeedPost

interface NewsFeedRepository {

    fun getNewsLoader(): StateFlow<List<FeedPost>>
    fun getAuthStateFlow(): Flow<AuthState>
    fun getComments(post: FeedPost): Flow<List<Comment>>

    suspend fun changeLikeStatus(post: FeedPost)

    suspend fun ignorePost(post: FeedPost)

    suspend fun loadNextData()


    suspend fun checkAuth()

}
