package com.example.careconnect.pages.afterauth

import android.app.Activity
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.careconnect.R
import com.example.careconnect.models.Services
import com.example.careconnect.pages.components.ServicesItem
import com.example.careconnect.repository.StorageManager

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HomePage(
    rootNavController:NavController,
    navController: NavController,
    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    val storage = StorageManager(context)
    val user by storage.storageData.collectAsState(initial = null)
    val activity = context as Activity
    val services = listOf(
        Services("appointment", R.drawable.appointment, "Appointment"),
        Services("tele-medical", R.drawable.telemedical, "Tele Medical"),
        Services("home-checkup", R.drawable.homecheckup, "Home Checkup",MaterialTheme.colorScheme.primary),
        Services("sickness-track", R.drawable.sicknesstrack, "Sickness Track",MaterialTheme.colorScheme.primary),
        Services("vital-signs", R.drawable.vitalsign, "Vital Signs",MaterialTheme.colorScheme.primary),
        Services("blood-request", R.drawable.bloodrequest, "Blood Request",MaterialTheme.colorScheme.primary),
        Services("blood-test", R.drawable.labtest, "Blood Test"),
        Services("emergency-service", R.drawable.ambulance, "Emergency Service",MaterialTheme.colorScheme.primary),
        Services("download-report", R.drawable.downloadreport, "Download Report",MaterialTheme.colorScheme.primary),
        Services("pharmacy", R.drawable.pharmacy, "Pharmacy",MaterialTheme.colorScheme.primary),
        Services("refill", R.drawable.refill, "Refill"),
        Services("radiology", R.drawable.radiology, "Radiology",MaterialTheme.colorScheme.primary),
        Services("patient-guide", R.drawable.guide, "Patient Guide",MaterialTheme.colorScheme.primary),
        Services("announcement", R.drawable.announcement, "Announcement",MaterialTheme.colorScheme.primary),
        Services("meal", R.drawable.lunch, "Meal",MaterialTheme.colorScheme.primary),
        Services("feedback", R.drawable.feedback, "Feedback",MaterialTheme.colorScheme.primary),
        Services("complain", R.drawable.complain, "Complain")
    )
    val sizeClass = calculateWindowSizeClass(activity = activity)
    val gridcell = when(sizeClass.widthSizeClass){
        WindowWidthSizeClass.Compact -> 3
        WindowWidthSizeClass.Medium -> 4
        WindowWidthSizeClass.Expanded -> 5
        else -> 3
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Have a good day!!",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = user?.name?.uppercase() ?: "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                FloatingActionButton(
                    onClick = {

                    },
                    modifier = Modifier.size(50.dp),
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bell),
                        contentDescription = "Notification Icon"
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                TextField(
                    value = "Search here",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .clip(RoundedCornerShape(50.dp)),
                    leadingIcon = {
                        Icon(Icons.Filled.Search, contentDescription = "Search Icon")
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent
                    )
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Services",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
//                Text(
//                    text = "View All",
//                    style = TextStyle(
//                        textDecoration = TextDecoration.Underline,
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 16.sp
//                    ),
//                    color = MaterialTheme.colorScheme.primary,
//                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ){
                LazyVerticalGrid(
                    columns = GridCells.Fixed(gridcell),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(services) { item ->
                        ServicesItem(rootNavController,item)
                    }
                }
            }
        }
    }
}