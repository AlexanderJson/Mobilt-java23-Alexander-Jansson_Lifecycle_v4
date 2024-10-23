package com.example.inl3


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inl3.Model.User
import com.example.inl3.RecyclerView.UserAdapter
import com.example.inl3.Repository.UserRepository
import com.example.inl3.Service.UserService
import com.example.inl3.ViewModel.UserViewModel
import com.example.inl3.ViewModel.UserViewModelFactory

class ExampleFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: UserAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_example, container, false)

        // reycycler view lista
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserAdapter(mutableListOf())
        recyclerView.adapter = adapter


        // init service och viewmodel
        val userRepository = UserRepository(requireContext())
        val userService = UserService(requireContext(),userRepository)
        val factory = UserViewModelFactory(userService)

        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

        userViewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            user?.let {
                adapter.newUsers(listOf(it))
            }
        }

            userViewModel.getUser(requireContext())

//        val user = MutableList<User> = mutableListOf(
//            User()
////        )
////
////
////        val adapter = UserAdapter(user)
//        recyclerView.adapter = adapter

        return view
    }
}
