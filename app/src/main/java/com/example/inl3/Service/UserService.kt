package com.example.inl3.Service

import android.content.Context
import android.util.Log
import com.example.inl3.Model.User
import com.example.inl3.Repository.UserRepository
import com.example.inl3.a.Repository.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserService(private val context: Context, private val userRepo: UserRepository) {


suspend fun updateUser(updatedUser: User){
    userRepo.updateUser(context, updatedUser)
}


    fun logoutUser(){
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
        Log.d("LOGOUT", "User logged out")
    }

    private fun saveUser(username: String){
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("username", username).apply()
        Log.d("SAVING USER: LOGIN", "User: $username")
    }

    suspend fun getUser(): List<User>? {
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val usernameInput = sharedPreferences.getString("username", null)
        return usernameInput?.let { userRepo.getUser(context, it) }
    }

    fun registerUser(context: Context, newUser: User){
        userRepo.registerUser(context, newUser)
    }

    fun getAllUsers(context: Context): List<User> {
        return userRepo.getUsers(context)
    }

    suspend fun authorizeUser(usernameInput: String, passwordInput: String): Boolean{
        // hämta användare från användarnamn
        val userList = userRepo.getUser(context, usernameInput)
        for (user in userList){
            // kolla lösenord
            if (user.password == passwordInput){
                saveUser(usernameInput)
                return true
            }
        }
        return false
    }

}


//suspend fun authorizeUser(username: String, password: String): Boolean = withContext(Dispatchers.IO)
//{   val user = userRepo.findByUsername(username)
//    if (user != null && user.password == password) {
//        userRepo.saveUser(username)
//        getUser()
//    }
//    return@withContext user != null
//}
//
//fun getPreferences(){
//    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
//    val username = sharedPreferences.getString("username", null)
//    Log.d("UserService", "User details: Username = $username")
//}
//
//fun clearPreferences(){
//    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
//    sharedPreferences.edit().clear().apply()
//}
//
//suspend fun getUser(): User? = withContext(Dispatchers.IO) {
//
//    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
//
//    val username = sharedPreferences.getString("username", null)
//    val user = userRepo.getUser(username)
//    if (user != null) {
//        Log.d("UserService", "User details: Username = ${user.username}, Password = ${user.password}, First Name = ${user.firstName}, Last Name = ${user.lastName}, Email = ${user.email}")
//    }
//
//    return@withContext user
//}
//
//
//fun loginUser(){}
//fun logoutUser(){}
//
//
//suspend fun addUser(newUser: User) = withContext(Dispatchers.IO) {
//    userRepo.addUser(newUser)
//}
//
//
//suspend fun updateUser(user: User) = withContext(Dispatchers.IO) {
//    userRepo.updateUser(user)
//    userRepo.saveUser(user.username)
//}
//
//fun deleteUser(){}
//
//fun viewUser(){}
//
//}