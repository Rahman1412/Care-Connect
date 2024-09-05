package com.example.careconnect.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItems (val route: String, val icon: ImageVector, val label: String){
    object Home : BottomNavItems("home", Icons.Default.Home, "Home")
    object Profile : BottomNavItems("profile", Icons.Default.Person, "Profile")
}