package com.example.inl3.db_logic

import android.content.Context
import android.util.Log

class UserService( private val context : Context) {


    private val userRepo = UserRepo(context)

    fun authorizeUser(username: String, password: String): Boolean
    {   val user = userRepo.findByUsername(username)
        if (user != null && user.password == password) {
            userRepo.saveUser(username)


            val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val savedUsername = sharedPreferences.getString("username", null)
            Log.d("UserService", "User saved in preferences: $savedUsername")
            getUser()

        }
        return user != null
    }



    fun getUser(): User? {

        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        val username = sharedPreferences.getString("username", null)
        val user = userRepo.getUser(username)
        if (user != null) {
            Log.d("UserService", "User details: Username = ${user.username}, Password = ${user.password}, First Name = ${user.firstName}, Last Name = ${user.lastName}, Email = ${user.email}")
        }

        return user
    }


    fun loginUser(){}
    fun logoutUser(){}

    fun addUser(newUser:  User){
        userRepo.addUser(newUser)
    }


    fun updateUser(user: User){
        println("SERVICE UPDATE STARTED")
        userRepo.updateUser(user)
    }

    fun deleteUser(){}

    fun viewUser(){}

}