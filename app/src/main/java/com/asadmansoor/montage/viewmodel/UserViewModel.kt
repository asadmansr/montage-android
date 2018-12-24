package com.asadmansoor.montage.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.asadmansoor.montage.Repository
import com.asadmansoor.montage.db.entity.User


class UserViewModel constructor (application: Application):  AndroidViewModel(application) {
    private var repository: Repository? = null
    private var allUsers: LiveData<List<User>>? = null

    init {
        repository = Repository(application)
        allUsers = repository?.getAllUsers()
    }

    fun insert(user: User){
        repository?.insert(user)
    }

    fun update(user: User){
        repository?.update(user)
    }

    fun delete(user: User){
        repository?.delete(user)
    }

    fun deleteAll(user: User){
        repository?.deleteAllUsers()
    }

    fun getUserList() : LiveData<List<User>>{
        return allUsers!!
    }
}