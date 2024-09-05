package com.example.careconnect.pages.afterauth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.careconnect.navigation.NavGraph
import com.example.careconnect.pages.components.WidthSpacer
import com.example.careconnect.pages.tabs.Cancel
import com.example.careconnect.pages.tabs.Complete
import com.example.careconnect.pages.tabs.Upcoming

@Composable
fun Appointment(
    rootNavController: NavController
){
    var tabIndex by rememberSaveable { mutableIntStateOf(0) }

    val tabs = listOf("Home", "About", "Settings")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(40.dp).clickable {
                            if(rootNavController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED){
                                rootNavController.navigateUp()
                            }
                        }
                    )
                    WidthSpacer(3)
                    Text(text = "Your Appointment", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                }
                FloatingActionButton(
                    onClick = {
                          rootNavController.navigate(NavGraph.NewAppointment){
                              launchSingleTop = true
                          }
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "New Appointment")
                }
            }
            TabRow(
                selectedTabIndex = tabIndex
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            when (tabIndex) {
                0 -> Upcoming()
                1 -> Complete()
                2 -> Cancel()
            }
        }
    }
}