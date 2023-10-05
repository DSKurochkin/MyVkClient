package ru.dm.myapps.clienvk.data.model.comments

import com.google.gson.annotations.SerializedName

data class CommentDto(
    @SerializedName("id") val id: Int,
    @SerializedName("from_id") val authorId: Long,
    @SerializedName("date") val publicationDate: Long,
    @SerializedName("text") val text: String
)
