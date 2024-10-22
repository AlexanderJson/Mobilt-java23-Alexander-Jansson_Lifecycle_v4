package com.example.inl3.Service

import android.content.Context
import android.util.Log
import com.example.inl3.Model.User
import com.example.inl3.a.Repository.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserService( private val context : Context) {


    private val userRepo = UserRepo(context)

    suspend fun authorizeUser(username: String, password: String): Boolean = withContext(Dispatchers.IO)
    {   val user = userRepo.findByUsername(username)
        if (user != null && user.password == password) {
            userRepo.saveUser(username)
            getUser()
        }
        return@withContext user != null
    }

    fun getPreferences(){
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", null)
        Log.d("UserService", "User details: Username = $username")
    }

    fun clearPreferences(){
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
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


    suspend fun addUser(newUser: User) = withContext(Dispatchers.IO) {
        userRepo.addUser(newUser)
    }


    suspend fun updateUser(user: User) = withContext(Dispatchers.IO) {
        userRepo.updateUser(user)
        userRepo.saveUser(user.username)
    }

    fun deleteUser(){}

    fun viewUser(){}

}