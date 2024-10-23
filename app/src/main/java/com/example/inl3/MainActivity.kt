package com.example.inl3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bottomNavigatonView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigatonView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_example -> {
                    loadFragment(ExampleFragment())
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