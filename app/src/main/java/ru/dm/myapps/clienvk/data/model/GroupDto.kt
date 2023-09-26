package ru.dm.myapps.clienvk.data.model

import com.google.gson.annotations.SerializedName

data class GroupDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("photo_200") val photoUrl: String
)
