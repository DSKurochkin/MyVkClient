package ru.dm.myapps.clienvk.presentation.main.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.dm.myapps.clienvk.domain.usecases.CheckAuthUseCase
import ru.dm.myapps.clienvk.domain.usecases.GetAuthStateUseCase
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    getAuthStateUseCase: GetAuthStateUseCase,
    private val checkAuthUseCase: CheckAuthUseCase
) : ViewModel() {

    val authState = getAuthStateUseCase()
    fun performResult() {
        viewModelScope.launch {
            checkAuthUseCase()
        }
    }
}