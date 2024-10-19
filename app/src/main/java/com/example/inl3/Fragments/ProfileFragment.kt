package com.example.inl3.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.inl3.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val usernameInput = view.findViewById<EditText>(R.id.username)
        val passwordInput = view.findViewById<EditText>(R.id.password)
        //mer här
        val loginBtn = view.findViewById<Button>(R.id.updateBtn)

        loginBtn.setOnClickListener {
            // fil auth method
        }


        return view

    }

    public fun authSuccess() {
        // uppdaterar fragment
    }

    public fun authFailure(){
        // felmeddelande
    }

    public fun checkDB(){

        // hämtar DB anslutning
    }
}