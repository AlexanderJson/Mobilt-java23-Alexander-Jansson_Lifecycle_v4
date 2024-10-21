package com.example.inl3.db_logic

import android.content.Context
import java.io.File
import java.io.IOException

class UserRepo(private val context: Context) {


    fun addUser(username: String, password: String, firstName: String, lastName: String, email: String): Boolean{
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

        val file = File("database.txt")

        if(file.exists()){
            val lines = file.readLines()
            val regex = Regex("u: (.+?) / p: (.+?) / fn: (.+?) / ln: (.+?) / e: (.+)")
            val updatedLines = mutableListOf<String>()

            val currentUsername = getUser()?.username //shared prefs
            val currentPassword = getUser()?.password //shared prefs

            if (currentUsername != null && currentPassword != null) {}
        }

        return true
    }




    fun getUser(): User? {
        // hämtar username input, söker databasen efter username
        val file = File("database.txt")

        // vid inlogg sparas navändar detalj på sharedPrefs för
        // att hämta alla andra

        val username = ("shared prefs")
        val password = ("shared prefs")

        if (file.exists()){
            val lines = file.readLines()
                val regex = Regex("u: (.+?) / p: (.+)")
                for (line in lines){
                    val matchResult = regex.find(line)
                    if (matchResult != null) {
                        val (storedUsername, storedPassword, storedfName, storedlName, storedEmail) = matchResult.destructured
                        if (storedUsername == username && storedPassword == password) {
                            return User(storedUsername, storedPassword, storedfName, storedlName, storedEmail)
                        }
                    }
                }
            }

        // logik för att söka db snabbt (id?)
        return null
    }





    fun deleteUser(userToken: Int, user: User): Boolean{

        // userToken skickas hit från input automatiskt (från sharedPrefs)
        // om userToken i sharedPrefs matchar med hemlig
        // nyckel i databasen uför följande:

        // hämta current user data på inloggad session (sharedprefs)
        // hitta user id efter user id
        //tar bort user objekt

        return true
    }



