package com.asadmansoor.montage.db.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.asadmansoor.montage.db.database.UserDatabase
import com.asadmansoor.montage.db.dao.UserDAO
import com.asadmansoor.montage.db.entity.User

class Repository(application : Application) {

    private var db: UserDatabase? = null
    private var userDAO: UserDAO? = null
    private var allUsers: LiveData<List<User>>? = null

    init {
        db =
            UserDatabase.getUserDatabase(application.applicationContext)
        userDAO = db?.userDAO()
        allUsers = userDAO?.getUserList()
    }

    fun insert(user: User){
        DoInsertAsync(userDAO!!).execute(user)
    }

    fun update(user: User){
        DoUpdateAsync(userDAO!!).execute(user)
    }

    fun delete(user: User){
        DoDeleteAsync(userDAO!!).execute(user)
    }

    fun deleteAllUsers(){
        DoDeleteAllAsync(userDAO!!).execute()
    }

    fun getAllUsers(): LiveData<List<User>>{
        return allUsers!!
    }

    private class DoInsertAsync(userDAO: UserDAO): AsyncTask<User, Void, Void>(){
        private var userDAO: UserDAO? = userDAO

        override fun doInBackground(vararg params: User): Void? {
            userDAO?.insert(params[0])
            return null
        }
    }

    private class DoUpdateAsync(userDAO: UserDAO): AsyncTask<User, Void, Void>(){
        private var userDAO: UserDAO? = userDAO

        override fun doInBackground(vararg params: User): Void? {
            userDAO?.update(params[0])
            return null
        }
    }

    private class DoDeleteAsync(userDAO: UserDAO): AsyncTask<User, Void, Void>(){
        private var userDAO: UserDAO? = userDAO

        override fun doInBackground(vararg params: User): Void? {
            userDAO?.delete(params[0])
            return null
        }
    }

    private class DoDeleteAllAsync(userDAO: UserDAO): AsyncTask<Void, Void, Void>(){
        private var userDAO: UserDAO? = userDAO

        override fun doInBackground(vararg params: Void?): Void? {
            userDAO?.deleteAll()
            return null
        }
    }
}

