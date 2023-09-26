package ru.dm.myapps.clienvk.data.model.post_attach

import com.google.gson.annotations.SerializedName

data class AttachPhotoDto(
    @SerializedName("sizes") val photoUrls: List<PhotoUrlDto>
)