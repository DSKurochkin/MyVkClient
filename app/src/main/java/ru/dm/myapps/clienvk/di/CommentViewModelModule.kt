package ru.dm.myapps.clienvk.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.dm.myapps.clienvk.presentation.comment.CommentsViewModel

@Module
interface CommentViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CommentsViewModel::class)
    fun bindCommentsViewModel(viewModel: CommentsViewModel): ViewModel
}