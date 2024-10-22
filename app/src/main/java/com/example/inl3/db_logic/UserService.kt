package com.example.inl3.db_logic

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserService( private val context : Context) {


    private val userRepo = UserRepo(context)

    suspend fun authorizeUser(username: String, password: String): Boolean = withContext(Dispatchers.IO)
    {   val user = userRepo.findByUsername(username)
        if (user != null && user.password == password) {
            userRepo.saveUser(username)


            val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val savedUsername = sharedPreferences.getString("username", null)
            Log.d("UserService", "User saved in preferences: $savedUsername")
            getUser()

        }
        return@withContext user != null
    }



    suspend fun getUser(): User? = withContext(Dispatchers.IO) {

        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        val username = sharedPreferences.getString("username", null)
        val user = userRepo.getUser(username)
        if (user != null) {
            Log.d("UserService", "User details: Username = ${user.username}, Password = ${user.password}, First Name = ${user.firstName}, Last Name = ${user.lastName}, Email = ${user.email}")
        }

        return@withContext user
    }


    fun loginUser(){}
    fun logoutUser(){}

    suspend fun addUser(newUser:  User) = withContext(Dispatchers.IO) {
        userRepo.addUser(newUser)
    }


    suspend fun updateUser(user: User) = withContext(Dispatchers.IO) {
        println("SERVICE UPDATE STARTED")
        userRepo.updateUser(user)
    }

    fun deleteUser(){}

    fun viewUser(){}

}