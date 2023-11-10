package ru.dm.myapps.clienvk.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.dm.myapps.clienvk.domain.AuthState
import ru.dm.myapps.clienvk.domain.NewsFeedRepository
import javax.inject.Inject

class GetAuthStateUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(): Flow<AuthState> {
        return repository.getAuthStateFlow()
    }

}