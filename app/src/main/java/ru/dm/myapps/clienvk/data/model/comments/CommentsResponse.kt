package ru.dm.myapps.clienvk.data.model.comments

import com.google.gson.annotations.SerializedName

data class CommentsResponse(
    @SerializedName("response") val content: CommentsResponseContainer
)
