package com.example.inl3.Security.Authorization

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.inl3.Activities.MainActivity
import com.example.inl3.R
import com.example.inl3.db_logic.User
import com.example.inl3.db_logic.UserService

class LoginFragment : Fragment() {

    private lateinit var userService: UserService

     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?

     ): View? {

         val view = inflater.inflate(R.layout.fragment_login, container, false)
         val username = view.findViewById<EditText>(R.id.username)
         val password = view.findViewById<EditText>(R.id.password)
         val email = view.findViewById<EditText>(R.id.email)
         val firstName = view.findViewById<EditText>(R.id.firstName)
         val lastName = view.findViewById<EditText>(R.id.lName)
         userService = UserService(requireContext())

         val loginBtn = view.findViewById<Button>(R.id.loginBtn)

         loginBtn.setOnClickListener {
             // fil auth method
             checkAuth(username.text.toString(), password.text.toString())

         }

         val registerBtn = view.findViewById<Button>(R.id.registerBtn)
         registerBtn.setOnClickListener {

             val user = User(
                 username = username.text.toString(),
                 password = password.text.toString(),
                 firstName = firstName.text.toString(),
                 lastName = lastName.text.toString(),
                 email = email.text.toString()
             )
             userService.addUser(user)
         }

         return view

     }



      private fun checkAuth(usernameInput: String, passwordInput: String): Boolean {

         val service = UserService(requireContext())
         val auth = service.authorizeUser(usernameInput, passwordInput)

         if (auth) {
             val intent = Intent(requireContext(), MainActivity::class.java)
             startActivity(intent)
             return true
         }
         return false
     }


}