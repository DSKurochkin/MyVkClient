package ru.dm.myapps.clienvk.domain

data class Comment(
    private val id: Int,
    private val authorName: String,
    private val authorAvatarResId: Int,
    private val text: String,
    private val publicationDate: String
)