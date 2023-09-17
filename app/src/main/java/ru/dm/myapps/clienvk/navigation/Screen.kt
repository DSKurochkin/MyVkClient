package ru.dm.myapps.clienvk.navigation

import ru.dm.myapps.clienvk.domain.FeedPost

sealed class Screen(val route: String) {

    object Home : Screen(ROUTE_HOME)
    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object Comments : Screen(ROUTE_COMMENTS) {
        private const val rootRoute = "comments"
        fun routeWithPostId(post: FeedPost): String = "$rootRoute/${post.id}"
    }

    object Favorite : Screen(ROUTE_FAVORITE)
    object PROFILE : Screen(ROUTE_PROFILE)


    companion object {
        private val ROUTE_HOME = "home"
        private val ROUTE_NEWS_FEED = "news_feed"
        private val ROUTE_COMMENTS = "comments/{post_id}"
        private val ROUTE_FAVORITE = "favorite"
        private val ROUTE_PROFILE = "profile"
        val KEY_POST_ID = "post_id"


    }
}

