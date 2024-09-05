package com.example.careconnect.pages.afterauth

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.careconnect.navigation.BottomNavItems
import com.example.careconnect.navigation.MainNavGraph
import com.example.careconnect.repository.StorageManager
import com.example.careconnect.viewmodel.HomeVm

@Composable
fun MainPage(rootNavController: NavController){
    val navController = rememberNavController()
    val bottomNavItem = listOf(
        BottomNavItems.Home,
        BottomNavItems.Profile
    )
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                bottomNavItem.forEach{ item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route){
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = item.label,
                                tint = if(currentRoute != item.route) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.scrim
                            )
                        },
                        label = {
                            Text(
                                text = item.label,
                                color = if(currentRoute != item.route) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.scrim
                            )
                        }
                    )
                }
            }
        },
    ){ paddingValues ->  
        MainNavGraph(rootNavController,navController,paddingValues)
    }
}