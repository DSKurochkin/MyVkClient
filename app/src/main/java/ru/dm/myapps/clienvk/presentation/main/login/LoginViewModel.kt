package ru.dm.myapps.clienvk.presentation.main.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult

class LoginViewModel : ViewModel() {

    private val _authState = MutableLiveData<AuthState>(AuthState.Initial)
    val authState: LiveData<AuthState>
        get() = _authState

    init {
        _authState.value =
            if (VK.isLoggedIn()) {
                AuthState.Authorized
            } else {
                AuthState.NotAuthorized
            }
    }

    fun perfomResult(result: VKAuthenticationResult) {
        when (result) {
            is VKAuthenticationResult.Success -> {
                _authState.value = AuthState.Authorized
            }

            is VKAuthenticationResult.Failed -> {
                _authState.value = AuthState.NotAuthorized
            }

            else -> {}
        }
    }
}