package ru.dm.myapps.clienvk.data.mapper

import ru.dm.myapps.clienvk.data.model.newsfeed.NewsFeedResponseDto
import ru.dm.myapps.clienvk.domain.FeedPost
import ru.dm.myapps.clienvk.domain.StatisticItem
import ru.dm.myapps.clienvk.domain.StatisticType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.absoluteValue

class NewsFeedMapper {
    fun responseToPosts(response: NewsFeedResponseDto): List<FeedPost> {
        val dtoPosts = response.content.posts
        val dtoGroups = response.content.group
        val posts = mutableListOf<FeedPost>()
        for (postDto in dtoPosts) {
            val groupDto = dtoGroups.find { it.id == postDto.sourceId.absoluteValue } ?: continue
            val post = FeedPost(
                id = postDto.id,
                sourceId = postDto.sourceId,
                communityName = groupDto.name,
                publicationDate = convertDate(postDto.date),
                communityImageUrl = groupDto.imageUrl,
                contentText = postDto.text,
                isLiked = postDto.likes.userLikes > 0,
                contentImageUrl = postDto.attachments
                    ?.firstOrNull()
                    ?.photo
                    ?.photoUrls
                    ?.lastOrNull()
                    ?.url,
                statisticItems = mutableMapOf(
                    StatisticType.VIEWS to StatisticItem(StatisticType.VIEWS, postDto.views.count),
                    StatisticType.COMMENTS to StatisticItem(
                        StatisticType.COMMENTS,
                        postDto.comments.count
                    ),
                    StatisticType.SHARES to StatisticItem(
                        StatisticType.SHARES,
                        postDto.reposts.count
                    ),
                    StatisticType.LIKES to StatisticItem(StatisticType.LIKES, postDto.likes.count)
                )
            )
            posts.add(post)
        }
        return posts
    }

    private fun convertDate(timestamp: Long): String {
        return SimpleDateFormat("d MM yyyy, hh:mm", Locale.getDefault())
            .format(Date(timestamp * 1000))
    }
}