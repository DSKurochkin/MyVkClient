package ru.dm.myapps.clienvk.data.model.post_attach

import com.google.gson.annotations.SerializedName

class PostAttachmentDto(
    @SerializedName("photo") val photo: AttachPhotoDto
)