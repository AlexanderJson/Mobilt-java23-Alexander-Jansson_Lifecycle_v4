package com.example.inl3

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
import com.example.inl3.Model.User
import com.example.inl3.Repository.UserRepository
import com.example.inl3.Service.UserService
import com.example.inl3.ViewModel.UserViewModel
import com.example.inl3.ViewModel.UserViewModelFactory
import com.example.inl3.a.Repository.UserRepo

class LoginFragment : Fragment() {

    private lateinit var rootView: View
    private lateinit var userService: UserService
    private lateinit var userRepo: UserRepository
    private lateinit var userViewModel: UserViewModel
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var email: EditText
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var loginBtn: Button



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        // view
        rootView = inflater.inflate(R.layout.fragment_login, container, false)

        userRepo = UserRepository(requireContext())
        userService = UserService(requireContext(),userRepo )

        val factory = UserViewModelFactory(userService)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)


        // UI
        username = rootView.findViewById(R.id.username)
        password = rootView.findViewById(R.id.password)
        email = rootView.findViewById(R.id.email)
        firstName = rootView.findViewById(R.id.firstName)
        lastName = rootView.findViewById(R.id.lName)

        loginBtn = rootView.findViewById(R.id.loginBtn)

        userService.getAllUsers(requireContext())

        val loginBtn = rootView.findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener {
            val usernameInput = username.text.toString()
            val passwordInput = password.text.toString()

            userViewModel.authorizeUser(usernameInput, passwordInput) {
                isAuthorized ->
                if (isAuthorized) {
                    Toast.makeText(requireContext(), "User found, login success!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    }
                else {
                    Toast.makeText(requireContext(), "Invalid log in", Toast.LENGTH_SHORT).show()
                }
            }
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
            userService.registerUser(requireContext(), user)
        }

        return rootView

    }




}
//private fun checkAuth(usernameInput: String, passwordInput: String) {
//
//    val isAuthorized = viewModel.authorizeUser(usernameInput, passwordInput)
//    if (isAuthorized){
//        Toast.makeText(requireContext(), "User found, login success!", Toast.LENGTH_SHORT).show()
//        val intent = Intent(requireContext(), MainActivity::class.java)
//        startActivity(intent)
//    }
//    else{
//        Toast.makeText(requireContext(), "Invalid log in", Toast.LENGTH_SHORT).show()
//    }
//}