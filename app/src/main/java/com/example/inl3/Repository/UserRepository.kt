package com.example.inl3.Repository

import android.content.Context
import android.widget.Toast
import com.example.inl3.Model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.util.Locale

class UserRepository(private val context: Context) {



     private fun writeJson(context: Context, jsonString: String){
        val file = File(context.filesDir, "users.json")
        FileOutputStream(file).use {
                outputStream -> outputStream.write(jsonString.toByteArray())
        }
    }

     private fun readJson(context: Context): String {
        val file = File(context.filesDir, "users.json")
        return if (file.exists()) {
            file.bufferedReader().use { it.readText() }
        } else {
            ""
        }
    }


    suspend fun updateUser(context: Context, updatedUser: User){
        return withContext(Dispatchers.IO) {
           val existingUserList = getUser(context, updatedUser.username)
            if (existingUserList.isNotEmpty()) {
                val jsonString = readJson(context)
                val usersArray = JSONArray(jsonString)

                for (i in 0 until usersArray.length()){
                    val userObject = usersArray.getJSONObject(i)
                    val username = userObject.getString("username")
                    if (username == updatedUser.username){
                        userObject.put("password", updatedUser.password)
                        userObject.put("firstName", updatedUser.firstName)
                        userObject.put("lastName", updatedUser.lastName)
                        userObject.put("email", updatedUser.email)
                        break
                    }
                }
                writeJson(context,usersArray.toString())
            } else {
                // skickar TOAST på main thread då det inte bör köras på annan
                return@withContext withContext(Dispatchers.Main){
                    Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }




    // hämtar en användare genom användarnamnet (suspendable)
     suspend fun getUser(context: Context, usernameInput: String): List<User> {
         // använder input/output tråd
        return withContext(Dispatchers.IO) {

            val jsonString = readJson(context)
            val userList = mutableListOf<User>()

            if (jsonString.isNotEmpty()) {
                val usersArray = JSONArray(jsonString)
                for (i in 0 until usersArray.length()) {
                    val userObject = usersArray.getJSONObject(i)
                    val username = userObject.getString("username")
                    if (username == usernameInput) {
                        val password = userObject.getString("password")
                        val firstName = userObject.getString("firstName")
                        val lastName = userObject.getString("lastName")
                        val email = userObject.getString("email")
                        val newUser = User(username, password, firstName, lastName, email)
                        userList.add(newUser)
                    }
                }
            }
            // returnera resultat av allt inuti withContext
            return@withContext userList
        }
    }

    // hämtar alla användare
         fun getUsers(context: Context): List<User> {
        val jsonString = readJson(context)
        val allUsersList = mutableListOf<User>()
        if (jsonString.isNotEmpty()){
            println("JSON File Content: $jsonString")
            val usersArray = JSONArray(jsonString)
            for (i in 0 until usersArray.length()){
                val userObject = usersArray.getJSONObject(i)
                val username = userObject.getString("username")
                val password = userObject.getString("password")
                val firstName = userObject.getString("firstName")
                val lastName = userObject.getString("lastName")
                val email = userObject.getString("email")
                val newUser = User(username, password, firstName, lastName, email)
                allUsersList.add(newUser)
                println("JSON File Content: $newUser")
            }
        }
        else {
            println("No users found")
        }
        return allUsersList
    }

    // lägger till användare
     fun registerUser(context: Context, newUser: User){
        val jsonString = readJson(context)
        val usersArray = if (jsonString.isNotEmpty()) {
            JSONArray(jsonString)
        } else {
            JSONArray()
        }

        for (i in 0 until usersArray.length()){
            val userObject = usersArray.getJSONObject(i)
            val existingUsername = userObject.getString("username").lowercase(Locale.getDefault())

            if (existingUsername == newUser.username){
                Toast.makeText(context, "User already exists", Toast.LENGTH_SHORT).show()
                return
            }
        }

        val newUserObject = JSONObject()
        newUserObject.put("username", newUser.username)
        newUserObject.put("password", newUser.password)
        newUserObject.put("firstName", newUser.firstName)
        newUserObject.put("lastName", newUser.lastName)
        newUserObject.put("email", newUser.email)
        usersArray.put(newUserObject)

        writeJson(context, usersArray.toString())
        Toast.makeText(context, "User ${newUser.username} added!", Toast.LENGTH_SHORT).show()

    }


}