package com.example.careconnect.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.careconnect.R
import com.example.careconnect.navigation.NavGraph
import com.example.careconnect.repository.StorageManager
import kotlinx.coroutines.delay

@Composable
fun Splash(rootNavController: NavController){
    val context = LocalContext.current
    val storage = StorageManager(context)
    val user by storage.storageData.collectAsState(initial = null)
    LaunchedEffect(Unit) {
        delay(200)
         if(user != null && user!!.accessToken != ""){
            rootNavController.navigate(NavGraph.Home) {
                popUpTo(NavGraph.Splash) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }else{
            rootNavController.navigate(NavGraph.Auth) {
                popUpTo(NavGraph.Splash) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Text(text = "Care", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = "Connect",fontSize = 25.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.inversePrimary)
        }
    }
}