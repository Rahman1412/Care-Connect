package com.example.careconnect.navigation

object NavGraph {
    const val Splash = "splash"
    const val Auth = "login"
    const val Home = "home"
    const val Appointment = "appointment"
    const val NewAppointment = "new-appointment"
    const val Doctors = "doctors"
}


sealed class AuthGraph(val route:String){
    object Login  : AuthGraph("login")
    object SignUp : AuthGraph("signup")
}