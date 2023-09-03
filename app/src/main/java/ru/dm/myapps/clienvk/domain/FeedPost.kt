package ru.dm.myapps.clienvk.domain

import ru.dm.myapps.clienvk.R

data class FeedPost(
    val communityName: String = "footballFriends",
    val publicationDate: String = "14:00",
    val avatarResId: Int = R.drawable.soccer_24,
    val contentText: String = "We are the champions - my friend.\n" +
            " And we will keep on fighting till the end. We are the champions",
    val contentImageResId: Int = R.drawable.pic,
    val statisticItems: Map<StatisticType, StatisticItem> = mapOf(
        StatisticType.VIEWS to StatisticItem(StatisticType.VIEWS, 999),
        StatisticType.SHARES to StatisticItem(StatisticType.SHARES, 800),
        StatisticType.COMMENTS to StatisticItem(StatisticType.COMMENTS, 300),
        StatisticType.LIKES to StatisticItem(StatisticType.LIKES, 700),
    )
)
