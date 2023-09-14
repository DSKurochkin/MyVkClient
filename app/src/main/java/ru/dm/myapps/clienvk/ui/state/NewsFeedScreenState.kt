package ru.dm.myapps.clienvk.ui.state

import ru.dm.myapps.clienvk.domain.FeedPost

sealed class NewsFeedScreenState {

    object Initial : NewsFeedScreenState()

    data class Posts(var posts: List<FeedPost>) : NewsFeedScreenState()

}
