package com.example.careconnect.pages.afterauth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.careconnect.navigation.NavGraph
import com.example.careconnect.repository.StorageManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@Composable
fun ProfilePage(
    rootNavController: NavController,
    navController: NavController,
    paddingValues: PaddingValues
){
    val context = LocalContext.current
    val storage = StorageManager(context)
    val user by storage.storageData.collectAsState(initial = null)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ){
        Column {
            Text(text = "Profile Page ${user?.accessToken.toString()}")
            Button(onClick = {
                CoroutineScope(Dispatchers.Main).launch {
                    rootNavController.navigate(NavGraph.Auth) {
                        popUpTo(NavGraph.Home) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                    withContext(Dispatchers.IO){
                        storage.clearPrefernces()
                    }
                }
            }) {
                Text("Logout")
            }
        }
    }
}