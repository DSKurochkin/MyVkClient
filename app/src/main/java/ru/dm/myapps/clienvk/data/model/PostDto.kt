package ru.dm.myapps.clienvk.data.model

import com.google.gson.annotations.SerializedName
import ru.dm.myapps.clienvk.data.model.post_attach.PostAttachmentDto

data class PostDto(
    @SerializedName("id") val id: String,
    @SerializedName("date") val date: Long,
    @SerializedName("text") val text: String,
    @SerializedName("is_favourite") val isFavourite: Boolean,
    @SerializedName("comments") val comments: CommentDto,
    @SerializedName("likes") val likes: LikesDto,
    @SerializedName("reposts") val reposts: RepostDto,
    @SerializedName("attachments") val attachments: List<PostAttachmentDto>

)