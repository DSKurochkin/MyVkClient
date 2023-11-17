package ru.dm.myapps.clienvk.domain.enity

import androidx.compose.runtime.Immutable

@Immutable
data class FeedPost(
    val id: Long,
    val sourceId: Long,
    val communityName: String,
    val publicationDate: String,
    val communityImageUrl: String,
    val contentText: String,
    val isLiked: Boolean,
    val contentImageUrl: String?,
    val statisticItems: Map<StatisticType, StatisticItem>,
    var commentsCount: Int = 0,
    var comments: List<Comment> = listOf()
)
