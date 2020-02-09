package com.asadmansoor.montage.db.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.asadmansoor.montage.db.entity.User
import com.asadmansoor.montage.db.dao.UserDAO

@Database(entities = [User::class], version = 4)
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