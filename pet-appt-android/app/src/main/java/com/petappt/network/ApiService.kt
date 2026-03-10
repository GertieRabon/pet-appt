package com.petappt.network

import com.petappt.data.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<String>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<String>

    @GET("appointments")
    suspend fun getAppointments(@Header("Authorization") token: String): Response<List<Appointment>>

    @POST("appointments")
    suspend fun createAppointment(
        @Header("Authorization") token: String,
        @Body request: AppointmentRequest
    ): Response<Appointment>

    @GET("pets")
    suspend fun getPets(@Header("Authorization") token: String): Response<List<Pet>>

    @GET("services")
    suspend fun getServices(@Header("Authorization") token: String): Response<List<GroomingService>>
}
