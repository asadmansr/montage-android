package com.asadmansoor.montage.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.asadmansoor.montage.db.entity.User
import com.asadmansoor.montage.db.dao.UserDAO

@Database(entities = [User::class], version = 2)
abstract class UserDatabase: RoomDatabase(){

    abstract fun userDAO(): UserDAO

    companion object {
        var instance: UserDatabase? = null

        fun getUserDatabase(context: Context): UserDatabase? {
            if (instance == null){
                synchronized(UserDatabase::class){
                    instance = Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return instance
        }

        fun destroyDatabase() {
            instance = null
        }
    }
}