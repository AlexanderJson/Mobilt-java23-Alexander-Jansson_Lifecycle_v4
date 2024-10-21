package com.example.inl3.db_logic

import android.content.Context
import java.io.File
import java.io.IOException

class UserRepo(private val context: Context) {


fun updateUser(updatedUser: User): Boolean {
    val file = File(context.filesDir, "database.txt")
    println("REPOSITORY UPDATE STARTED")
    println("User details to be updated: Username = ${updatedUser.username}, Password = ${updatedUser.password}, First Name = ${updatedUser.firstName}, Last Name = ${updatedUser.lastName}, Email = ${updatedUser.email}")

    if (!file.exists()) {
        println("File does not exist.")
        return false
    }

    try {

        val lines = file.readLines().toMutableList()
        val regex = Regex("u: (.+?) / p: (.+?) / fn: (.+?) / ln: (.+?) / e: (.+)")
        var updated = false
        for (i in lines.indices) {
            val match = regex.find(lines[i])
            if (match != null) {
                    lines[i] =
                        "u: ${updatedUser.username} / p: ${updatedUser.password} / fn: ${updatedUser.firstName} / ln: ${updatedUser.lastName} / e: ${updatedUser.email}"
                    updated = true
                    break
            }
        }
        if (updated) {
            file.writeText(lines.joinToString("\n"))
            println("User ${updatedUser.username} updated. Rewriting file.")
            return true
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return false
}




    fun addUser(newUser: User): Boolean {

        val file = File(context.filesDir, "database.txt")
        try {

            if (!file.exists()){
                file.createNewFile()
            }

            val existingUser = findByUsername(newUser.username)
            if (existingUser != null) {
                println("User with username ${newUser.username} already exists! Not adding.")
                return false
            }
            file.appendText("u: ${newUser.username} / p: ${newUser.password} / fn: ${newUser.firstName} / ln: ${newUser.lastName} / e: ${newUser.email}")
            return true
        }
        catch(_: IOException){
            return false
        }
    }



    fun getUser(username: String?): User? {

        try {
            val file = File(context.filesDir, "database.txt")
            val lines = file.readLines()

            val regex = Regex("u: (.+?) / p: (.+?) / fn: (.+?) / ln: (.+?) / e: (.+?)")
            for (line in lines) {
                val matchResult = regex.find(line)
                if (matchResult != null) {
                    val (storedUsername, storedPassword, storedfName, storedlName, storedEmail) = matchResult.destructured
                    if (storedUsername == username) {
                        return User(
                            storedUsername,
                            storedPassword,
                            storedfName,
                            storedlName,
                            storedEmail
                        )

                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun saveUser(username: String){
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.apply()
    }

    fun checkAuth(username: String, password: String): Boolean{
        val file = File(context.filesDir, "database.txt")

        if (file.exists()){
            val lines = file.readLines()
            val regex = Regex("u: (.+?) / p: (.+)")
            for (line in lines){
                val matchResult = regex.find(line)
                if (matchResult != null) {
                    val (storedUsername, storedPassword, storedfName, storedlName, storedEmail) = matchResult.destructured
                    if (storedUsername == username && storedPassword == password) {
                        return true
                    }
                }
            }
        }
        return false
    }


    fun findByUsername(username: String): User?{
       try{
           val file = File(context.filesDir, "database.txt")
           val lines = file.readLines()

           val regex = Regex("u: (.+?) / p: (.+?) / fn: (.+?) / ln: (.+?) / e: (.+)")
           for (line in lines) {
               val matchResult = regex.find(line)
               if (matchResult != null) {
                   val (storedUsername, storedPassword, storedfName, storedlName, storedEmail) = matchResult.destructured
                   println("Findby: Checking username: [$storedUsername] against [$username]")

                   if (storedUsername == username) {
                       return User(storedUsername, storedPassword, storedfName, storedlName, storedEmail)
                   }
               }
           }


       }catch (e: IOException){
           e.printStackTrace()
       }
            return null
        }
    }


fun addUser(username: String, password: String, firstName: String, lastName: String, email: String): Boolean{
    /*
    val file = File("database.txt")

    if(findByUsername(username,password) != null) {
        return false
    }

    try{
        file.appendText("u: $username / p: $password / fn: $firstName / ln: $lastName / e: $email\n")
        return true
    }catch (e: IOException){
        e.printStackTrace()
        return false
    }

     */
    return false
}















