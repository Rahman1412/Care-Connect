package com.example.careconnect.pages.afterauth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.careconnect.R
import com.example.careconnect.models.Doctors
import com.example.careconnect.pages.components.WidthSpacer

@Composable
fun Doctor(item:Doctors){
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()
    ElevatedCard(
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            if(item.image.contains("svg")){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.image)
                        .build(),
                    contentDescription = item.doctor_name,
                    imageLoader = imageLoader,
                    error = painterResource(R.drawable.errorimage),
                    modifier = Modifier.size(100.dp)
                )
            }else{
                AsyncImage(
                    model = item.image,
                    contentDescription = "Profile Image",
                    error = painterResource(R.drawable.errorimage),
                    modifier = Modifier.size(100.dp)
                )
            }
            Column {
                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = item.doctor_name,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(3.dp)
                    )
                    Text(text = "General Practitioner",modifier = Modifier.padding(3.dp))
                    Row(
                        modifier = Modifier.padding(3.dp)
                    ){
                        Row {
                            Icon(painter = painterResource(id = R.drawable.experience), contentDescription = "Experience", tint = MaterialTheme.colorScheme.primary)
                            WidthSpacer(5)
                            Text(text = item.doctor_experience+" Years")
                        }
                        WidthSpacer(15)
                        item.rating?.let{
                            Row {
                                Image(painter = painterResource(id = R.drawable.heart), contentDescription = "Heart")
                                WidthSpacer(5)
                                Text(text = "${it} %")
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.padding(3.dp)
                    ){
                        Text(
                            text = "Monday To Friday",
                            modifier = Modifier.background(color = MaterialTheme.colorScheme.primaryContainer).padding(start = 30.dp, end = 30.dp, top = 5.dp, bottom = 5.dp)
                        )
                    }
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Make An Appointment")
                }
            }
        }
    }
}