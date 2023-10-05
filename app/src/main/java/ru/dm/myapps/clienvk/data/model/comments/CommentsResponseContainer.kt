package ru.dm.myapps.clienvk.data.model.comments

import com.google.gson.annotations.SerializedName

data class CommentsResponseContainer(
    @SerializedName("items") val comments: List<CommentDto>,
    @SerializedName("profiles") val profiles: List<ProfileDto>,

    )
