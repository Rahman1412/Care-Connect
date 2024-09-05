package com.example.careconnect.models

data class LoginData(
    val email:LoginFieldState = LoginFieldState(),
    val password:LoginFieldState = LoginFieldState()
)
data class LoginFieldState(
    val value:String = "",
    val touched : Boolean = false,
    val untouched: Boolean = false,
    val error : String = ""
)

