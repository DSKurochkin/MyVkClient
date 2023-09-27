package ru.dm.myapps.clienvk.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.dm.myapps.clienvk.data.model.NewsFeedResponseDto

interface ApiService {

    @GET("newsfeed.getRecommended?v=5.150")
    suspend fun loadNews(
        @Query("access_token") token: String
    ): NewsFeedResponseDto
}