package com.example.careconnect.models

data class AuthUser(
    val access_token: String,
    val address: String,
    val appointment_date: Any,
    val dob: String,
    val email: String,
    val gender: String,
    val id: Int,
    val image: String,
    val iquma_no: String,
    val mobile_no: String,
    val mrn_no: String,
    val name: String,
    val name_ar: String,
    val otp: String,
    val patient_blood_group: String,
    val patient_id: String
)