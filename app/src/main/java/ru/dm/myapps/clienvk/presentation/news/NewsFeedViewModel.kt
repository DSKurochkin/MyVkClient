package ru.dm.myapps.clienvk.presentation.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.dm.myapps.clienvk.domain.enity.FeedPost
import ru.dm.myapps.clienvk.domain.usecases.ChangeLikeStatusUseCase
import ru.dm.myapps.clienvk.domain.usecases.GetNewsLoaderUseCase
import ru.dm.myapps.clienvk.domain.usecases.IgnorePostUseCase
import ru.dm.myapps.clienvk.domain.usecases.LoadNextDataUseCase
import ru.dm.myapps.clienvk.extensions.withFlow
import javax.inject.Inject

class NewsFeedViewModel @Inject constructor(
    getNewsLoaderUseCase: GetNewsLoaderUseCase,
    private val loadNextDataUseCase: LoadNextDataUseCase,
    private val ignorePostUseCase: IgnorePostUseCase,
    private val changeLikeStatusUseCase: ChangeLikeStatusUseCase,
) : ViewModel() {


    private val recommendationStateFlow = getNewsLoaderUseCase()

    private val loadNextDataFlow = MutableSharedFlow<NewsFeedScreenState>()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        Log.d("NewsFeedViewModel", "Exception was caught by Exception Handler")
    }

    val screenState = recommendationStateFlow
        .filter { it.isNotEmpty() }
        .map { NewsFeedScreenState.Posts(it) as NewsFeedScreenState }
        .onStart { emit(NewsFeedScreenState.Loading) }
        .withFlow(loadNextDataFlow)


    fun loadNextNews() {
        viewModelScope.launch {
            loadNextDataFlow.emit(
                NewsFeedScreenState.Posts(
                    posts = recommendationStateFlow.value,
                    isLoading = true
                )
            )
            loadNextDataUseCase()
        }
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            changeLikeStatusUseCase(feedPost)

        }
    }

    fun deletePost(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            ignorePostUseCase(feedPost)

        }
    }
}