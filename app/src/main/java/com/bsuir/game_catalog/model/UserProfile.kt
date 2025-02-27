package com.bsuir.game_catalog.model

data class UserProfile(
    var url: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var nickname: String = "",
    var birthDate: String = "",
    var phone: String = "",
    var address: String = "",
    var city: String = "",
    var country: String = "",
    var description: String = "",
    var profession: String = ""
)
