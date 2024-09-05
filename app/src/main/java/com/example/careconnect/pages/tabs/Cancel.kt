package com.example.careconnect.pages.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Cancel(){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = "Cancel Appointment")
    }
}