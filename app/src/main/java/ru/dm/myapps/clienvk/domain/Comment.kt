package ru.dm.myapps.clienvk.domain

data class Comment(
    val id: Int,
    val authorName: String,
    val authorAvatarUrl: String,
    val text: String,
    val publicationDate: String
)