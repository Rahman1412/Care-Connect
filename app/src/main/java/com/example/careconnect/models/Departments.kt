package com.example.careconnect.models

data class Departments(
    val department_code: String,
    val department_id: String,
    val description_ar: String,
    val description_eng: String,
    val doctor_count: Int,
    val image: String
)