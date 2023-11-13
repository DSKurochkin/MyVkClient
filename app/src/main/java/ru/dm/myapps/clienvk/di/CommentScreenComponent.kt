package ru.dm.myapps.clienvk.di

import dagger.BindsInstance
import dagger.Subcomponent
import ru.dm.myapps.clienvk.domain.enity.FeedPost
import ru.dm.myapps.clienvk.presentation.ViewModelFactory

@Subcomponent(modules = [CommentViewModelModule::class])
interface CommentScreenComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance post: FeedPost): CommentScreenComponent
    }


}