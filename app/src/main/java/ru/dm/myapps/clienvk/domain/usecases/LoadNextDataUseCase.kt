package ru.dm.myapps.clienvk.domain.usecases

import ru.dm.myapps.clienvk.domain.NewsFeedRepository
import javax.inject.Inject

class LoadNextDataUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() {
        repository.loadNextData()
    }

}