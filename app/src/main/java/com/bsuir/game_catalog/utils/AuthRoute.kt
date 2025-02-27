package com.bsuir.game_catalog.utils

import com.bsuir.game_catalog.viewmodel.AuthViewModel

object AuthRoute {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val MAIN = "profile"

    fun getInitialRoute(authViewModel: AuthViewModel): String =
        if (authViewModel.user.value != null) MAIN else LOGIN
}