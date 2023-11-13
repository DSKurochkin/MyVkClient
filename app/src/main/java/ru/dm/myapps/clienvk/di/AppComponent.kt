package ru.dm.myapps.clienvk.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.dm.myapps.clienvk.presentation.main.MainActivity

@AppScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)

    fun getCommentScreenComponentFactory(): CommentScreenComponent.Factory

    @Component.Factory
    interface AppComponentFactory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent

    }
}