package ru.dm.myapps.clienvk.domain

data class FeedPost(
    val id: String,
    val communityName: String,
    val publicationDate: String,
    val communityImageUrl: String,
    val contentText: String,
    val contentImageUrl: String?,
    val statisticItems: Map<StatisticType, StatisticItem>
)
