package ru.dm.myapps.clienvk.presentation.main.login

sealed class AuthState {

    object Initial : AuthState()
    object Authorized : AuthState()
    object NotAuthorized : AuthState()

}