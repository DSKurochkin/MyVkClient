package ru.dm.myapps.clienvk.data.model.newsfeed

import com.google.gson.annotations.SerializedName
import ru.dm.myapps.clienvk.data.model.newsfeed.post_attach.PostAttachmentDto

data class PostDto(
    @SerializedName("id") val id: Long,
    @SerializedName("source_id") val sourceId: Long,
    @SerializedName("date") val date: Long,
    @SerializedName("text") val text: String,
    @SerializedName("comments") val comments: CommentDto,
    @SerializedName("likes") val likes: LikesDto,
    @SerializedName("reposts") val reposts: RepostDto,
    @SerializedName("attachments") val attachments: List<PostAttachmentDto>?,
    @SerializedName("views") val views: ViewsDto

)