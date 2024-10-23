package com.example.inl3.UI.Auth

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
import com.example.inl3.R
import com.example.inl3.Repository.UserRepository
import com.example.inl3.Service.UserService
import com.example.inl3.ViewModel.UserViewModel
import com.example.inl3.ViewModel.UserViewModelFactory

class RegisterUserFragment : Fragment() {
    private lateinit var rootView: View
    private lateinit var userService: UserService
    private lateinit var userRepo: UserRepository
    private lateinit var userViewModel: UserViewModel

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var email: EditText
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var registerBtn: Button



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        // view
        rootView = inflater.inflate(R.layout.fragment_register, container, false)

        userRepo = UserRepository(requireContext())
        userService = UserService(requireContext(),userRepo )

        val factory = UserViewModelFactory(userService)
        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]


        // UI
        username = rootView.findViewById(R.id.username)
        password = rootView.findViewById(R.id.password)
        email = rootView.findViewById(R.id.email)
        firstName = rootView.findViewById(R.id.firstName)
        lastName = rootView.findViewById(R.id.lName)

        val registerBtn = rootView.findViewById<Button>(R.id.registerBtn)
        registerBtn.setOnClickListener {
            val user = User(
                username = username.text.toString(),
                password = password.text.toString(),
                firstName = firstName.text.toString(),
                lastName = lastName.text.toString(),
                email = email.text.toString()
            )
            if (user.username.isNotEmpty() && user.password.isNotEmpty()) {
                userViewModel.registerUser(requireContext(), user)

                userViewModel.userLiveData.observe(viewLifecycleOwner) { registeredUser ->
                    registeredUser?.let {
                        Toast.makeText(requireContext(), "User registered", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            else {
                Toast.makeText(requireContext(), "Please fill in username and password", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView

    }
}