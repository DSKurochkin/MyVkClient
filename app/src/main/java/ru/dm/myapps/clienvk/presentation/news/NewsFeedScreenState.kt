package ru.dm.myapps.clienvk.presentation.news

import ru.dm.myapps.clienvk.domain.enity.FeedPost

sealed class NewsFeedScreenState {

    object Initial : NewsFeedScreenState()
    object Loading : NewsFeedScreenState()

    data class Posts(var posts: List<FeedPost>, var isLoading: Boolean = false) :
        NewsFeedScreenState()

}
