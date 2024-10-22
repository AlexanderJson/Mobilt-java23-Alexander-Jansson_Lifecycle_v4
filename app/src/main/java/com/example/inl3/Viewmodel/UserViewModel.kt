package com.example.inl3.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.inl3.db_logic.User
import com.example.inl3.db_logic.UserService
import kotlinx.coroutines.launch

class UserViewModel(private val userService: UserService) : ViewModel() {

 // "container" som håller all data, uppdaterar UI vid förändring
 val userData: MutableLiveData<User?> = MutableLiveData()

    fun authorizeUser(username: String, password: String): LiveData<Boolean> = liveData {
        val isAuthorized = userService.authorizeUser(username, password)
        emit(isAuthorized)
    }

    // fyller userData med hämtad data (för lista)
    fun getUser() {
        viewModelScope.launch {
            val user = userService.getUser()
            userData.postValue(user)
        }
    }

    // uppdaterar UI med ny data (för lista + update logik)
    fun updateUser(user: User) {
        viewModelScope.launch {
            userService.updateUser(user)
            getUser()
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            userService.addUser(user)
            getUser()
        }
    }



}