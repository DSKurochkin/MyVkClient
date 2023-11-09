package ru.dm.myapps.clienvk.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.dm.myapps.clienvk.domain.AuthState
import ru.dm.myapps.clienvk.domain.NewsFeedRepository

class GetAuthStateUseCase(private val repository: NewsFeedRepository) {
    operator fun invoke(): Flow<AuthState> {
        return repository.getAuthStateFlow()
    }

}