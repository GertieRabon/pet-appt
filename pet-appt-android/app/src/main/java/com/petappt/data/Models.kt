package com.petappt.data

data class User(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: String
)

data class LoginRequest(val email: String, val password: String)
data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)

data class Pet(
    val id: Long? = null,
    val name: String,
    val breed: String,
    val age: Int,
    val weight: Double,
    val ownerId: Long? = null
)

data class Appointment(
    val id: Long? = null,
    val dateTime: String,
    val status: String,
    val pet: Pet?,
    val service: GroomingService?,
    val owner: User?
)

data class GroomingService(
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val duration: Int
)

data class AppointmentRequest(
    val dateTime: String,
    val petId: Long,
    val serviceId: Long,
    val ownerId: Long
)
