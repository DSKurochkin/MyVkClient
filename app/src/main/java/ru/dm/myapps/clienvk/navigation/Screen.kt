package ru.dm.myapps.clienvk.navigation

import android.net.Uri
import com.google.gson.Gson
import ru.dm.myapps.clienvk.domain.FeedPost

sealed class Screen(val route: String) {

    object Home : Screen(ROUTE_HOME)
    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object Comments : Screen(ROUTE_COMMENTS) {
        fun routeWithPostId(post: FeedPost): String {
            val jsonPost = Gson().toJson(post)
            val encodingString = Uri.encode(jsonPost)
            return "comments/${encodingString}"
        }
    }

    object Favorite : Screen(ROUTE_FAVORITE)
    object PROFILE : Screen(ROUTE_PROFILE)


    companion object {
        val KEY_POST = "post"
        private val ROUTE_HOME = "home"
        private val ROUTE_NEWS_FEED = "news_feed"
        private val ROUTE_COMMENTS = "comments/{$KEY_POST}"
        private val ROUTE_FAVORITE = "favorite"
        private val ROUTE_PROFILE = "profile"


    }
}

