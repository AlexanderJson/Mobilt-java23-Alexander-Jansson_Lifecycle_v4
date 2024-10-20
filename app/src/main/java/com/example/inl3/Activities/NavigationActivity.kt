package com.example.inl3.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.inl3.Fragments.HomeFragment
import com.example.inl3.Fragments.LogoutFragment
import com.example.inl3.Fragments.ProfileFragment
import com.example.inl3.R
import com.google.android.material.bottomnavigation.BottomNavigationView

open class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){

                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                R.id.logout -> {
                   // loadFragment(LogoutFragment())
                    true
                }
                else -> false

            }



        }


    }
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}