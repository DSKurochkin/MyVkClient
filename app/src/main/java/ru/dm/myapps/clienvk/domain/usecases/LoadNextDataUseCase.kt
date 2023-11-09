package ru.dm.myapps.clienvk.domain.usecases

import ru.dm.myapps.clienvk.domain.NewsFeedRepository

class LoadNextDataUseCase(private val repository: NewsFeedRepository) {
    suspend operator fun invoke() {
        repository.loadNextData()
    }

}