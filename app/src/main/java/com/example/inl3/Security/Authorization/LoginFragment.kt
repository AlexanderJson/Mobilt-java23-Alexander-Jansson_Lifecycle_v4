package com.example.inl3.Security.Authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.inl3.R

 class LoginFragment : Fragment() {

     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {

         val view = inflater.inflate(R.layout.fragment_login, container, false)
        val usernameInput = view.findViewById<EditText>(R.id.username)
         val passwordInput = view.findViewById<EditText>(R.id.password)
         val loginBtn = view.findViewById<Button>(R.id.loginBtn)

         loginBtn.setOnClickListener {
             // fil auth method
         }


         return view

     }
     public fun checkAuth(usernameInput: String, passwordInput: String): Boolean {
         return false
     }

     public fun authSuccess() {}

     public fun authFailure(){}

     public fun checkDB(){}
}