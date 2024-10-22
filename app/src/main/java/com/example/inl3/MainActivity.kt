package com.example.inl3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.inl3.Model.User
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val newUser = User("alex", "password123", "Alex", "Johnson", "alex.johnson@example.com")

        registerUser(this, newUser)


        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    // Hämta RecyclerViewFragment
                    true
                }
                R.id.profileFragment -> {
                    // hämta EditUserFragment
                    true
                }
                else -> false
            }
        }





    }

    private fun writeJson(context: Context, jsonString: String){
        val file = File(context.filesDir, "users.json")
        FileOutputStream(file).use {
                outputStream -> outputStream.write(jsonString.toByteArray())
        }

    }

    private fun readJson(context: Context): String {
        val json: String
        try {
            val inputStream: InputStream = context.assets.open("users.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
        return json
    }

    private fun registerUser(context: Context, newUser: User){
        val jsonString = readJson(context)
        val usersArray = if (jsonString.isNotEmpty()) {
            JSONArray(jsonString)
        } else {
            JSONArray()
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