package ru.dm.myapps.clienvk.presentation

import android.app.Application
import ru.dm.myapps.clienvk.di.AppComponent
import ru.dm.myapps.clienvk.di.DaggerAppComponent

class NewsFeedApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(
            this
        )
    }
}