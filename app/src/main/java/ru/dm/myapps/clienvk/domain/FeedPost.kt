package ru.dm.myapps.clienvk.domain

data class FeedPost(
    val id: Long,
    val sourceId: Long,
    val communityName: String,
    val publicationDate: String,
    val communityImageUrl: String,
    val contentText: String,
    val isLiked: Boolean,
    val contentImageUrl: String?,
    val statisticItems: Map<StatisticType, StatisticItem>
)
