package com.example.inl3.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inl3.Model.User
import com.example.inl3.Service.UserService
import kotlinx.coroutines.launch

class UserViewModel(private val userService: UserService) : ViewModel() {

 // "container" som håller all data, uppdaterar UI vid förändring
 private val userData = MutableLiveData<User?>()

 // listener för all data inuti userData
 val userLiveData: LiveData<User?> = userData


 // hämtar en användare från shared prefs, uppdaterar userData
 fun getUser(context: Context) {
  viewModelScope.launch {
   val user = userService.getUser()?.firstOrNull()
   userData.postValue(user)
  }
 }

     fun updateUser(updatedUser: User) {
        viewModelScope.launch {
           userService.updateUser(updatedUser)

            val updatedUserList = userService.getUser()
            updatedUserList?.let {
                if(it.isNotEmpty()) {
                    userData.postValue(it[0])
                }
            }

        }
    }

 fun authorizeUser(username: String, password: String, onResult: (Boolean) -> Unit){
  // VMScope håller reda på livscyceln av viewmodeln och stoppar coroutines
  // ifall viewmodelen inte längre är aktiv (förhindra minnesläckor)
  viewModelScope.launch {
   val isAuthorized = userService.authorizeUser(username, password)
   onResult(isAuthorized)
  }
 }


}




//    fun authorizeUser(username: String, password: String): LiveData<Boolean> = liveData {
//        val isAuthorized = userService.authorizeUser(username, password)
//        emit(isAuthorized)
//    }
//
//    // fyller userData med hämtad data (för lista)
//    fun getUser() {
//        viewModelScope.launch {
//            val user = userService.getUser()
//            userData.postValue(user)
//        }
//    }
//
//    // uppdaterar UI med ny data (för lista + update logik)
//    fun updateUser(user: User) {
//        viewModelScope.launch {
//            userService.updateUser(user)
//            getUser()
//        }
//    }
//
//    fun addUser(user: User) {
//        viewModelScope.launch {
//            userService.addUser(user)
//        }
//    }
