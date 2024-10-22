package com.example.inl3.Model

data class User (
    val username: String,
    val password: String,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
)