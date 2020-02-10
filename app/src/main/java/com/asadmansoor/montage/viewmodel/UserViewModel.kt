package com.asadmansoor.montage.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.asadmansoor.montage.db.database.UserDatabase
import com.asadmansoor.montage.db.repository.UserRepository
import com.asadmansoor.montage.db.entity.User
import kotlinx.coroutines.launch


class UserViewModel (application: Application):  AndroidViewModel(application) {
    private val userRepository: UserRepository
    val allUsers: LiveData<List<User>>

    init {
        val userDao = UserDatabase.getUserDatabase(application)!!.userDAO()
        userRepository = UserRepository(userDao)
        allUsers = userRepository.allUsers
    }

    fun insert(user: User) = viewModelScope.launch {
        userRepository.insert(user)
    }

    fun delete(user: User) = viewModelScope.launch {
        userRepository.delete(user)
    }

    fun deleteAll() = viewModelScope.launch {
        userRepository.deleteAll()
    }
}