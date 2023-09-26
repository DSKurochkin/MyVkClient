package ru.dm.myapps.clienvk.data.model

import com.google.gson.annotations.SerializedName

data class NewsFeedResponseDto(
    @SerializedName("response") val content: NewsFeedContentDto
)
