package ru.dm.myapps.clienvk.presentation.main.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.dm.myapps.clienvk.data.repository.NewsFeedRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NewsFeedRepository(application)
    val authState = repository.authStateFlow
    fun performResult() {
        viewModelScope.launch {
            repository.checkAuth()
        }
    }
}