package com.example.careconnect.models

import com.google.gson.JsonElement


data class ApiResponse(
    val status : Boolean? = null,
    val data : JsonElement? = null,
    val message : String? = null
)
