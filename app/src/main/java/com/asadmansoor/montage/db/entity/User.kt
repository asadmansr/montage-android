package com.asadmansoor.montage.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val name: String,
    val imgRes: Int,
    val colorRes: Int
)