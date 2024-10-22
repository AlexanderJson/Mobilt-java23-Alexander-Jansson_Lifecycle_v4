package com.example.inl3.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.inl3.R
import com.example.inl3.Viewmodel.UserViewModel
import com.example.inl3.Viewmodel.UserViewModelFactory
import com.example.inl3.db_logic.User
import com.example.inl3.db_logic.UserService

class EditUserFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var     userServices: UserService
    private lateinit var usernameEdit: EditText
    private lateinit var passwordEdit: EditText
    private lateinit var emailEdit: EditText
    private lateinit var firstNameEdit: EditText
    private lateinit var lastNameEdit: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userServices = UserService(requireContext())


        val factory = UserViewModelFactory(userServices)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        usernameEdit = view.findViewById(R.id.username)
        passwordEdit = view.findViewById(R.id.password)
        emailEdit = view.findViewById(R.id.email)
        firstNameEdit = view.findViewById(R.id.firstName)
        lastNameEdit = view.findViewById(R.id.lName)


        userViewModel.userData.observe(viewLifecycleOwner) { user ->
            user?.let {
                usernameEdit.setText(it.username)
                passwordEdit.setText(it.password)
                emailEdit.setText(it.email)
                firstNameEdit.setText(it.firstName)
                lastNameEdit.setText(it.lastName)
            }
        }

        userViewModel.getUser()

        val updateBtn = view.findViewById<Button>(R.id.editBtn)
        updateBtn.setOnClickListener {
            println("BUTTON STARTED")

            val updatedUser = User(
                    usernameEdit.text.toString(),
                    passwordEdit.text.toString(),
                    firstNameEdit.text.toString(),
                    lastNameEdit.text.toString(),
                    emailEdit.text.toString()
                )
                userViewModel.updateUser(updatedUser)
            }
        }
    }
