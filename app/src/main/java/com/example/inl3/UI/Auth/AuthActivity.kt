package com.example.inl3.UI.Auth

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.inl3.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)

        val authBottomMenu = findViewById<BottomNavigationView>(R.id.bottom_navigation_auth)

        // default är login
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.auth_container, LoginFragment())
                .commit()
        }

        authBottomMenu.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_login -> {
                    loadFragment(LoginFragment())
                    true
                }
                R.id.nav_register -> {
                    loadFragment(RegisterUserFragment())
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
            .replace(R.id.auth_container, chosenFragment)
            .commit()
    }


}