package ru.dm.myapps.clienvk.presentation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import ru.dm.myapps.clienvk.di.AppComponent
import ru.dm.myapps.clienvk.di.DaggerAppComponent

class NewsFeedApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}

@Composable
fun getApplicationComponent(): AppComponent {
    return (LocalContext.current.applicationContext as NewsFeedApplication).component
}