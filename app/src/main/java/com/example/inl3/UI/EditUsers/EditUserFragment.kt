package com.example.inl3.UI.EditUsers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.inl3.Model.User
import com.example.inl3.R
import com.example.inl3.RecyclerView.UserAdapter
import com.example.inl3.Repository.UserRepository
import com.example.inl3.Service.UserService
import com.example.inl3.ViewModel.UserViewModel
import com.example.inl3.ViewModel.UserViewModelFactory

class EditUserFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userServices: UserService
    private lateinit var userRepository: UserRepository
    private lateinit var adapter: UserAdapter

    // input
    private lateinit var usernameText: TextView
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

        userRepository = UserRepository(requireContext())
        userServices = UserService(requireContext(), userRepository)

        val factory = UserViewModelFactory(userServices)
        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

        usernameText = view.findViewById(R.id.username)
        passwordEdit = view.findViewById(R.id.password)
        emailEdit = view.findViewById(R.id.email)
        firstNameEdit = view.findViewById(R.id.firstName)
        lastNameEdit = view.findViewById(R.id.lName)

        userViewModel.getUser(requireContext())
        userViewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            user?.let {
                usernameText.text = it.username
                passwordEdit.setText(it.password)
                emailEdit.setText(it.email)
                firstNameEdit.setText(it.firstName)
                lastNameEdit.setText(it.lastName)
            }
        }

        val updateButton = view.findViewById<Button>(R.id.editBtn)
        updateButton.setOnClickListener {
            val updatedUser = User(
                usernameText.text.toString(),
                passwordEdit.text.toString(),
                firstNameEdit.text.toString(),
                lastNameEdit.text.toString(),
                emailEdit.text.toString()
            )
            userViewModel.updateUser(updatedUser)
            Toast.makeText(context, "User updated", Toast.LENGTH_SHORT).show()
        }


    }
}
