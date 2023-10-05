package ru.dm.myapps.clienvk.data.model.comments

import com.google.gson.annotations.SerializedName

data class ProfileDto(
    @SerializedName("id") val id: Long,
    @SerializedName("first_name") val name: String,
    @SerializedName("last_name") val surname: String,
    @SerializedName("photo_200") val avatarUrl: String
)
