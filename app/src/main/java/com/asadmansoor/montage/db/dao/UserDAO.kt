package com.asadmansoor.montage.db.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import com.asadmansoor.montage.db.entity.User


@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM user_table")
    fun getUserList(): LiveData<List<User>>
}