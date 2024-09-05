package com.example.careconnect.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.careconnect.pages.afterauth.HomePage
import com.example.careconnect.pages.afterauth.ProfilePage

@Composable
fun MainNavGraph(rootNavController: NavController,navController: NavHostController,paddingValues: PaddingValues){
    NavHost(navController = navController, startDestination = "home") {
        composable("home"){
            HomePage(rootNavController,navController,paddingValues)
        }
        
        composable("profile"){
            ProfilePage(rootNavController,navController,paddingValues)
        }
    }
}