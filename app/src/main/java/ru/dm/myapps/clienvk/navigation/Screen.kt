package ru.dm.myapps.clienvk.navigation

sealed class Screen(val route: String) {

    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object Favorite : Screen(ROUTE_FAVORITE)
    object PROFILE : Screen(ROUTE_PROFILE)
    object COMMENT : Screen(ROUTE_COMMENT)

    companion object {
        private val ROUTE_NEWS_FEED = "news_feed"
        private val ROUTE_FAVORITE = "favorite"
        private val ROUTE_PROFILE = "profile"
        private val ROUTE_COMMENT = "Comment"
    }
}

