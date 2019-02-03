package com.asadmansoor.montage.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val uid: Int? = null,
    val name: String,
    val email: String,
    val imgRes: Int,
    val colorRes: Int,
    val username: String,
    val password: String,
    val phone: String,
    val city: String,
    val state: String,
    val timezone: String
)