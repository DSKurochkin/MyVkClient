package ru.dm.myapps.clienvk.domain

data class Comment(
    val id: Int,
    val authorName: String,
    val authorAvatarResId: Int,
    val text: String,
    val publicationDate: String
)