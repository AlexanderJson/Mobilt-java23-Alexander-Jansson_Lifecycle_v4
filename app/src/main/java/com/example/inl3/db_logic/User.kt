package com.example.inl3.db_logic

data class User (
    val username: String,
    val password: String,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
)