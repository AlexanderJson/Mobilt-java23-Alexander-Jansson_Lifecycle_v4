package com.example.inl3.UI

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.inl3.UI.Auth.AuthActivity
import com.example.inl3.UI.Homepage.HomeFragment
import com.example.inl3.R
import com.example.inl3.Repository.UserRepository
import com.example.inl3.Service.UserService
import com.example.inl3.UI.EditUsers.EditUserFragment
import com.example.inl3.UI.ViewUsers.ViewUserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository
    private lateinit var userService: UserService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        userRepository = UserRepository(this)
        userService = UserService(this, userRepository)

        val bottomNavigatonView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigatonView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_view_users -> {
                    loadFragment(ViewUserFragment())
                    true
                }
                R.id.nav_edit_users -> {
                    loadFragment(EditUserFragment())
                    true
                }
                R.id.nav_logout -> {
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    userService.logoutUser()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }


    private fun loadFragment(chosenFragment: Fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, chosenFragment)
                .commit()
    }
}