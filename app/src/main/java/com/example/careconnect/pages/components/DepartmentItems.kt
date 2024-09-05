package com.example.careconnect.pages.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.careconnect.R
import com.example.careconnect.models.Departments
import com.example.careconnect.navigation.NavGraph
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun DepartmentItems(rootNavController: NavController,item:Departments){
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()
    ListItem(
        headlineContent = {
            Text(item.description_eng, fontWeight = FontWeight.Bold)
        },
        supportingContent = { Text("${item.doctor_count} Doctor") },
        trailingContent = {
            Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "Next")
        },
        leadingContent = {
            if (item.image.contains("svg")) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.image)
                        .build(),
                    contentDescription = item.description_eng,
                    imageLoader = imageLoader,
                    error = painterResource(R.drawable.errorimage),
                    modifier = Modifier.size(60.dp)
                )
            } else {
                AsyncImage(
                    model = item.image,
                    contentDescription = item.description_eng,
                    error = painterResource(R.drawable.errorimage),
                    modifier = Modifier.size(60.dp)
                )
            }
        },
        modifier = Modifier.clickable {
            val department = Gson().toJson(item)
            rootNavController.navigate(
                NavGraph.Doctors+"?department=$department"
            ){
                launchSingleTop = true
            }
        }
    )
    HorizontalDivider()
}

