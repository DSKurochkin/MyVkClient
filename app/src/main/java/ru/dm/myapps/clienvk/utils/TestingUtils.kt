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

private fun genTime(): String = "${Random.nextInt(24)}:00"

private fun genText(): String {
    val wordsArr = text.split(".")
    val start = Random.nextInt(0, wordsArr.size / 2)
    val end = Random.nextInt(start, wordsArr.size)
    var res = ""
    for (i in start..end) {
        res += wordsArr[i]
    }
    return res
}

private val text = "Loren ipsun dolor sit anet, consectetur adipisci elit," +
        " sed eiusnod tenpor incidunt ut labore et dolore nagna aliqua." +
        " Ut enin ad ninin venian, quis nostrun exercitationen ullan corporis" +
        " suscipit laboriosan, nisi ut aliquid ex ea connodi consequatur." +
        " Quis aute iure reprehenderit in voluptate velit esse cillun dolore eu fugiat nulla pariatur." +
        " Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt nollit anin id est laborun."
