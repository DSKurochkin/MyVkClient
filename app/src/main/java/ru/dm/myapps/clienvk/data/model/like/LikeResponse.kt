package ru.dm.myapps.clienvk.data.model.like

import com.google.gson.annotations.SerializedName

data class LikeResponse(
    @SerializedName("response") val like: Like
)
