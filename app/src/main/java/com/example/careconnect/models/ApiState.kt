package com.example.careconnect.models

sealed class ApiState {
    object Success: ApiState()
    object Error: ApiState()
    object Loading : ApiState()
}