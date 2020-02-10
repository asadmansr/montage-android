package com.asadmansoor.montage.db.repository

import androidx.lifecycle.LiveData
import com.asadmansoor.montage.db.dao.UserDAO
import com.asadmansoor.montage.db.entity.User

class UserRepository(private val userDao: UserDAO) {
    val allUsers: LiveData<List<User>> = userDao.getUserList()

    suspend fun insert(user: User){
        userDao.insert(user)
    }

    suspend fun delete(user: User){
        userDao.delete(user)
    }

    suspend fun deleteAll(){
        userDao.deleteAll()
    }
}

