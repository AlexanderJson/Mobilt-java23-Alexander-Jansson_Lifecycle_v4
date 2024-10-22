package com.example.inl3.Security.Authorization

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.inl3.Activities.MainActivity
import com.example.inl3.R
import com.example.inl3.Viewmodel.UserViewModel
import com.example.inl3.Viewmodel.UserViewModelFactory
import com.example.inl3.db_logic.User
import com.example.inl3.db_logic.UserService
class LoginFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel


    private lateinit var rootView: View
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var email: EditText
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var loginBtn: Button
    private lateinit var registerBtn: Button



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        // view
        rootView = inflater.inflate(R.layout.fragment_login, container, false)

        val userService = UserService(requireContext())

        val factory = UserViewModelFactory(userService)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)


        // UI
        username = rootView.findViewById(R.id.username)
        password = rootView.findViewById(R.id.password)
        email = rootView.findViewById(R.id.email)
        firstName = rootView.findViewById(R.id.firstName)
        lastName = rootView.findViewById(R.id.lName)

        loginBtn = rootView.findViewById(R.id.loginBtn)

        val loginBtn = rootView.findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener {
            // fil auth method
            checkAuth(username.text.toString(), password.text.toString())

        }

        val registerBtn = rootView.findViewById<Button>(R.id.registerBtn)
        registerBtn.setOnClickListener {

            val user = User(
                username = username.text.toString(),
                password = password.text.toString(),
                firstName = firstName.text.toString(),
                lastName = lastName.text.toString(),
                email = email.text.toString()
            )
            userViewModel.addUser(user)
        }

        return rootView

    }



    private fun checkAuth(usernameInput: String, passwordInput: String) {

        //val auth = userViewModel.authorizeUser(usernameInput, passwordInput)
        userViewModel.authorizeUser(usernameInput, passwordInput).observe(viewLifecycleOwner) { loggedIn ->

            if (loggedIn) {
                Toast.makeText(requireContext(), "User found, login success!", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(requireContext(), "Invalid log in", Toast.LENGTH_SHORT).show()
            }
        }
    }
}