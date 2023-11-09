package ru.dm.myapps.clienvk.presentation.main.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.dm.myapps.clienvk.data.repository.NewsFeedRepositoryImpl
import ru.dm.myapps.clienvk.domain.usecases.CheckAuthUseCase
import ru.dm.myapps.clienvk.domain.usecases.GetAuthStateUseCase

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NewsFeedRepositoryImpl(application)
    private val getAuthStateUseCase = GetAuthStateUseCase(repository)
    private val checkAuthUseCase = CheckAuthUseCase(repository)
    val authState = getAuthStateUseCase()
    fun performResult() {
        viewModelScope.launch {
            checkAuthUseCase()
        }
    }
}