package com.asadmansoor.montage.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.asadmansoor.montage.db.entity.User


@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAll()

    @Query("SELECT * FROM user_table WHERE name == :uid")
    fun getUser(uid: Int): LiveData<List<User>>

    @Query("SELECT * FROM user_table")
    fun getUserList(): LiveData<List<User>>
}