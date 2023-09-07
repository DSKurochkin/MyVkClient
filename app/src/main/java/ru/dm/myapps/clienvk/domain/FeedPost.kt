package ru.dm.myapps.clienvk.domain

import ru.dm.myapps.clienvk.R
import kotlin.random.Random

data class FeedPost(
    val id: Int = 1,
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
) {
    companion object {
        fun genFeedPost(id: Int): FeedPost {
            return FeedPost(
                id = id + 10,
                communityName = "Football community ${Random.nextInt(1000)}",
                publicationDate = "${Random.nextInt(24)}:00",
                contentText = genText(),
            )
        }

        private fun genText(): String {
            var res = ""
            repeat(Random.nextInt(30, 150)) {
                res += Char(Random.nextInt(65, 123))
            }
            return res
        }
    }
}
