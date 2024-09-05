package com.example.careconnect.models

data class Doctors(
    val available_days: List<String>,
    val department_id: String,
    val doctor_ar_name: String,
    val doctor_code: String,
    val doctor_experience: String,
    val doctor_id: String,
    val doctor_name: String,
    val doctor_speciality: String,
    val gender: Any,
    val id: Int,
    val image: String,
    val mobile_no: String,
    val rating: Any,
    val speciality_code: String,
    val status: String
)