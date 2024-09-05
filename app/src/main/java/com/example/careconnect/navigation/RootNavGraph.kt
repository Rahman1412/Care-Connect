package com.example.careconnect.navigation

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.careconnect.models.Departments
import com.example.careconnect.pages.Splash
import com.example.careconnect.pages.afterauth.Appointment
import com.example.careconnect.pages.afterauth.DoctorsList
import com.example.careconnect.pages.afterauth.MainPage
import com.example.careconnect.pages.afterauth.NewAppointment
import com.example.careconnect.pages.beforeauth.LoginPage
import com.example.careconnect.repository.StorageManager
import com.example.careconnect.repository.User
import com.google.gson.Gson
import kotlinx.coroutines.async

@Composable
fun RootNavGraph() {
    val rootNavController = rememberNavController()
    NavHost(navController = rootNavController, startDestination = NavGraph.Splash){
        composable(NavGraph.Splash){
            Splash(rootNavController)
        }

        composable(AuthGraph.Login.route){
            LoginPage(rootNavController)
        }

        composable(BottomNavItems.Home.route){
            MainPage(rootNavController)
        }

        composable(NavGraph.Appointment){
            Appointment(rootNavController)
        }

        composable(NavGraph.NewAppointment){
            NewAppointment(rootNavController)
        }

        composable(NavGraph.Doctors+"?department={department}",
                arguments = listOf(
                navArgument(
                    name = "department"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
            )
        ){ backStackEntry ->
            val departmentJson =  backStackEntry.arguments?.getString("department")
            val department = Gson().fromJson(departmentJson, Departments::class.java)
            DoctorsList(rootNavController,department)
        }
    }
}