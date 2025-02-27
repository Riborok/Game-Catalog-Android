package com.bsuir.game_catalog.utils

import com.bsuir.game_catalog.viewmodel.AuthViewModel

object Route {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val PROFILE = "profile"

    fun getInitialRoute(authViewModel: AuthViewModel): String =
        if (authViewModel.user.value != null) PROFILE else LOGIN
}