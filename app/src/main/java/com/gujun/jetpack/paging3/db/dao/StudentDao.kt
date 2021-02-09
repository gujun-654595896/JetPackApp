package com.gujun.jetpack.paging3.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gujun.jetpack.paging3.db.entity.Student3

/**
 *    author : gujun
 *    date   : 2021/2/9 10:39
 *    desc   :
 */
@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStudent(student: Student3)

    @Query("select * from table_student")
    fun getAllStudent(): List<Student3>


}