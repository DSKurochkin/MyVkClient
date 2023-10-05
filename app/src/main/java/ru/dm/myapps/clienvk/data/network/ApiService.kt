package ru.dm.myapps.clienvk.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.dm.myapps.clienvk.data.model.comments.CommentsResponse
import ru.dm.myapps.clienvk.data.model.like.LikeResponse
import ru.dm.myapps.clienvk.data.model.newsfeed.NewsFeedResponseDto
import ru.dm.myapps.clienvk.data.model.newsfeed.RepostDto

interface ApiService {

    //@GET("newsfeed.getRecommended?v=5.150")
    @GET("newsfeed.getRecommended?v=5.150&count=10") //for testing only
    suspend fun loadNews(
        @Query("access_token") token: String
    ): NewsFeedResponseDto

    //@GET("newsfeed.getRecommended?v=5.150")
    @GET("newsfeed.getRecommended?v=5.150&count=10") //for testing only
    suspend fun loadNews(
        @Query("access_token") token: String,
        @Query("start_from") startFrom: String?
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

    @GET("newsfeed.ignoreItem?type=wall&v=5.150")
    suspend fun ignorePost(
        @Query("access_token") token: String,
        @Query("owner_id") ownerId: Long,
        @Query("item_id") itemId: Long
    ): RepostDto

    @GET("wall.getComments?v=5.150&sort=desc&extended=1&fields=photo_200")
    suspend fun getComments(
        @Query("access_token") token: String,
        @Query("owner_id") ownerId: Long,
        @Query("post_id") postId: Long
    ): CommentsResponse
}