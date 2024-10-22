package com.example.inl3.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inl3.Fragments.EditUserFragment
import com.example.inl3.R
import com.example.inl3.UserAdapter
import com.example.inl3.Viewmodel.UserViewModel
import com.example.inl3.Viewmodel.UserViewModelFactory
import com.example.inl3.db_logic.UserService
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userServices: UserService
    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)


        userServices = UserService(this)


        val factory = UserViewModelFactory(userServices)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        userAdapter = UserAdapter(mutableListOf())
        recyclerView.adapter = userAdapter

        userViewModel.userData.observe(this) { user ->
            user?.let {
                userAdapter.updateUsers(listOf(it))
            }
        }

        userViewModel.getUser()


        val updateBtn = findViewById<Button>(R.id.updateBtn)
        updateBtn.setOnClickListener {
            showEditUserFragment()
        }


        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId){
                R.id.homeFragment -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.profileFragment -> {
                    true
                }
                else -> false
            }
        }
    }

    private fun showEditUserFragment() {
        val editUserFragment = EditUserFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, editUserFragment)
            .addToBackStack(null)
            .commit()

        findViewById<FrameLayout>(R.id.fragment_container).visibility = View.VISIBLE
    }
}