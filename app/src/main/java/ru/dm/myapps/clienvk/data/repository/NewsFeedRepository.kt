package ru.dm.myapps.clienvk.data.repository

import android.app.Application
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import ru.dm.myapps.clienvk.data.mapper.NewsFeedMapper
import ru.dm.myapps.clienvk.data.network.ApiFactory
import ru.dm.myapps.clienvk.data.network.ApiService
import ru.dm.myapps.clienvk.domain.FeedPost

class NewsFeedRepository(val application: Application) {

    private val api: ApiService = ApiFactory.apiService
    private val mapper = NewsFeedMapper()

    private val _posts = mutableListOf<FeedPost>()
    val posts: List<FeedPost>
        get() = _posts.toList()

    suspend fun loadNews() {
        val storage = VKPreferencesKeyValueStorage(application)
        val token = VKAccessToken.restore(storage) ?: throw RuntimeException("Invalid token")
        _posts.addAll(mapper.responseToPosts(api.loadNews(token.accessToken)))
    }
}