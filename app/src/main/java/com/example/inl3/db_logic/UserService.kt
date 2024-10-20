package com.example.inl3.db_logic

import android.content.Context

class UserService( private val context : Context) {


    private val userRepo = UserRepo(context)

    fun authorizeUser(username: String, password: String): Boolean
    {   val user = userRepo.findByUsername(username, password)
        return user != null
    }

    fun loginUser(){}
    fun logoutUser(){}

    fun addUser(){

    }


    fun updateUser(){}

    fun deleteUser(){}

    fun viewUser(){}

}