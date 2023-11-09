package ru.dm.myapps.clienvk.domain

sealed class AuthState {

    object Initial : AuthState()
    object Authorized : AuthState()
    object NotAuthorized : AuthState()

}