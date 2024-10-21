package com.example.inl3.db_logic

import android.content.Context
import java.io.File
import java.io.IOException

class UserRepo(private val context: Context) {

    fun getUser(username: String?): User? {

        try {
            val assetManager = context.assets
            val inputStream = assetManager.open("database")
            val lines = inputStream.bufferedReader().use { it.readLines() }

            val regex = Regex("u: (.+?) / p: (.+?) / fn: (.+?) / ln: (.+?) / e: (.+)")
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


    fun findByUsername(username: String, password: String): User?{
       try{
           val assetManager = context.assets
           val inputStream = assetManager.open("database")
           val lines = inputStream.bufferedReader().use { it.readLines() }

           val regex = Regex("u: (.+?) / p: (.+?) / fn: (.+?) / ln: (.+?) / e: (.+)")
           for (line in lines) {
               val matchResult = regex.find(line)
               if (matchResult != null) {
                   val (storedUsername, storedPassword, storedfName, storedlName, storedEmail) = matchResult.destructured
                   if (storedUsername == username && storedPassword == password) {
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





    fun checkAuth(username: String, password: String): Boolean{
        val file = File("database.txt")

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






    fun updateUser(username: String, password: String, firstName: String, lastName: String, email: String): Boolean{



        return true
    }





