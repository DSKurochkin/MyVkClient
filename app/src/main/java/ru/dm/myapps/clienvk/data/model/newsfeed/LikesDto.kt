package ru.dm.myapps.clienvk.data.model.newsfeed

import com.google.gson.annotations.SerializedName

data class LikesDto(
    @SerializedName("count") val count: Int,
    @SerializedName("user_likes") val userLikes: Int
)
