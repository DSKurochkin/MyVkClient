package ru.dm.myapps.clienvk.data.model.newsfeed

import com.google.gson.annotations.SerializedName

data class RepostDto(
    @SerializedName("count") val count: Int
)
