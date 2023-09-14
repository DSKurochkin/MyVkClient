package ru.dm.myapps.clienvk.ui.state

import ru.dm.myapps.clienvk.domain.Comment
import ru.dm.myapps.clienvk.domain.FeedPost

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(val post: FeedPost, val comments: List<Comment>) : CommentsScreenState()

}
