package com.example.careconnect.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private val retrofit = Retrofit.Builder().baseUrl(Services.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    val api: Endpoints = retrofit.create(Endpoints::class.java)
}