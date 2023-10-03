package ru.dm.myapps.clienvk.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.dm.myapps.clienvk.data.model.like.LikeResponse
import ru.dm.myapps.clienvk.data.model.newsfeed.NewsFeedResponseDto

interface ApiService {

    @GET("newsfeed.getRecommended?v=5.150")
    suspend fun loadNews(
        @Query("access_token") token: String
    ): NewsFeedResponseDto

    @GET("likes.add?type=post&v=5.150")
    suspend fun addLike(
        @Query("access_token") token: String,
        @Query("owner_id") ownerId: Long,
        @Query("item_id") itemId: Long
    ): LikeResponse

    @GET("likes.delete?type=post&v=5.150")
    suspend fun deleteLike(
        @Query("access_token") token: String,
        @Query("owner_id") ownerId: Long,
        @Query("item_id") itemId: Long
    ): LikeResponse
}