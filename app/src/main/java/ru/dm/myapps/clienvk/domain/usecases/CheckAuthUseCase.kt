package ru.dm.myapps.clienvk.domain.usecases

import ru.dm.myapps.clienvk.domain.NewsFeedRepository

class CheckAuthUseCase(private val repository: NewsFeedRepository) {
    suspend operator fun invoke() {
        repository.checkAuth()
    }

}