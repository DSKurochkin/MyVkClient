package ru.dm.myapps.clienvk.domain.usecases

import kotlinx.coroutines.flow.StateFlow
import ru.dm.myapps.clienvk.domain.NewsFeedRepository
import ru.dm.myapps.clienvk.domain.enity.FeedPost

class GetNewsLoaderUseCase(private val repository: NewsFeedRepository) {
    operator fun invoke(): StateFlow<List<FeedPost>> {
        return repository.getNewsLoader()
    }

}