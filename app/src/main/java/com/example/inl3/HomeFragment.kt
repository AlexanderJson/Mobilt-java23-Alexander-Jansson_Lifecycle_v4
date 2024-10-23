package com.example.inl3


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inl3.RecyclerView.UserAdapter
import com.example.inl3.Repository.UserRepository
import com.example.inl3.Service.UserService
import com.example.inl3.ViewModel.UserViewModel
import com.example.inl3.ViewModel.UserViewModelFactory

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)



        return view
    }
}
