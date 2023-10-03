package ru.dm.myapps.clienvk.data.model.like

import com.google.gson.annotations.SerializedName

data class Like(
    @SerializedName("likes") val count: Int
)
