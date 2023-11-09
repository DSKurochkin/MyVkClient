package ru.dm.myapps.clienvk.presentation.comment

import ru.dm.myapps.clienvk.domain.enity.Comment
import ru.dm.myapps.clienvk.domain.enity.FeedPost

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(val post: FeedPost, val comments: List<Comment>) : CommentsScreenState()

}
