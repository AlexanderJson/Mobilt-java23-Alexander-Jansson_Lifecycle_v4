package com.example.inl3.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.inl3.R
import com.example.inl3.db_logic.UserService

class ProfileFragment : Fragment() {

   lateinit var userServices: UserService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        userServices = UserService(requireContext())
        return inflater.inflate(R.layout.fragment_edit_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = userServices.getUser()
        val editBtn = view.findViewById<Button>(R.id.editBtn)
        editBtn.setOnClickListener {
        }
    }
}