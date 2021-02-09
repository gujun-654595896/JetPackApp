package com.gujun.jetpack.paging3.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *    author : gujun
 *    date   : 2021/2/5 14:03
 *    desc   :
 */
@Entity(tableName = "table_student")
data class Student3(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo
    val id: String,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val age: Int
)