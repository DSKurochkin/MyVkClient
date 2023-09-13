package ru.dm.myapps.clienvk.ui

import ru.dm.myapps.clienvk.domain.Comment
import ru.dm.myapps.clienvk.domain.FeedPost

sealed class HomeScreenState {

    class Initial() : HomeScreenState()

    data class Posts(var posts: List<FeedPost>) : HomeScreenState()
    data class Comments(val post: FeedPost, val comments: List<Comment>) : HomeScreenState()

}
