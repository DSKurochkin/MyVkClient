package ru.dm.myapps.clienvk.presentation

import android.app.Application
import ru.dm.myapps.clienvk.di.AppComponent
import ru.dm.myapps.clienvk.di.DaggerAppComponent
import ru.dm.myapps.clienvk.domain.enity.FeedPost

class NewsFeedApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(
            this, FeedPost(
                0, 0, "", "", "", "", false, "", mapOf(), 0, listOf()
            )
        )
    }
}