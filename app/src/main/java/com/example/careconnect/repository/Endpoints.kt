package com.example.careconnect.repository

import com.example.careconnect.models.Login
import com.example.careconnect.models.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Endpoints {
    @POST("login")
    suspend fun Login(@Body login: Login) : Response<ApiResponse>

    @GET("department")
    suspend fun GetDepartment() : Response<ApiResponse>

    @GET("doctor")
    suspend fun GetDoctors(
        @Query("department_id") departmentId: Int
    ) : Response<ApiResponse>
}