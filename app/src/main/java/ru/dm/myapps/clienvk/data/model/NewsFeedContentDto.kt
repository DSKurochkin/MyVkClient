package ru.dm.myapps.clienvk.data.model

import com.google.gson.annotations.SerializedName

data class NewsFeedContentDto(
    @SerializedName("items") val posts: List<PostDto>,
    @SerializedName("groups") val group: List<GroupDto>

)
