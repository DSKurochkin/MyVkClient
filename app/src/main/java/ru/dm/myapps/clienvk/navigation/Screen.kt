package ru.dm.myapps.clienvk.navigation

sealed class Screen(val route: String) {

    object Home : Screen(ROUTE_HOME)
    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object Comments : Screen(ROUTE_COMMENTS)
    object Favorite : Screen(ROUTE_FAVORITE)
    object PROFILE : Screen(ROUTE_PROFILE)


    companion object {
        private val ROUTE_HOME = "home"
        private val ROUTE_NEWS_FEED = "news_feed"
        private val ROUTE_COMMENTS = "comments"
        private val ROUTE_FAVORITE = "favorite"
        private val ROUTE_PROFILE = "profile"
    }
}

