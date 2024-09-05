package com.example.careconnect.pages.components

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable

@Composable
fun DisplayToast(context: Context, msg:String){
    var toast : Toast? = null
    toast?.cancel()
    toast = Toast.makeText(context,msg,Toast.LENGTH_SHORT)
    toast?.show()
}