package ru.dm.myapps.clienvk.utils

import ru.dm.myapps.clienvk.R
import ru.dm.myapps.clienvk.domain.Comment
import ru.dm.myapps.clienvk.domain.FeedPost
import kotlin.random.Random

fun genFeedPost(id: Int): FeedPost {
    return FeedPost(
        id = id + 10,
        communityName = "Football community ${Random.nextInt(1000)}",
        publicationDate = genTime(),
        contentText = genText(),
    )
}

fun genComment(id: Int): Comment {
    return Comment(
        id = id,
        authorName = "Author $id",
        authorAvatarResId = R.drawable.user_455,
        text = genText(),
        publicationDate = genTime()
    )
}

private fun genText(): String {
    var res = ""
    repeat(Random.nextInt(20, 150)) {
        res += Char(Random.nextInt(65, 123))
    }
    return res
}

private fun genTime(): String = "${Random.nextInt(24)}:00"