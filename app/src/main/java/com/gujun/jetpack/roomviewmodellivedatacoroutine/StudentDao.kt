package com.gujun.jetpack.roomviewmodellivedatacoroutine

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 *    author : gujun
 *    date   : 2021/2/1 10:49
 *    desc   : 协程相关的操作  数据库操作（增、删、改、查）接口，@Insert、@Delete、@Update 和 @Query
 */

//添加Room中的@Dao注解
@Dao
interface StudentDao {

    //@Query 查询注解，它的参数是String类型，我们直接写SQL语句进行执行
    @Query("select * from table_students_coroutine where sid =:id")
    fun getStudentById(id: Long): LiveData<Student>

    //@Query 查询注解，它的参数是String类型，我们直接写SQL语句进行执行
    @Query("select * from table_students_coroutine")
    fun getAllStudent(): LiveData<List<Student>>

    //插入数据，onConflict代表已经存在这个数据之后如何操作（OnConflictStrategy.REPLACE替换）
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStudent(student: Student)

    //删除某条数据
    @Delete
    suspend fun deleteStudent(student: Student)

    //更新某条数据
    @Update
    suspend fun updateStudent(student: Student)

    //@Query参数可以是sql语句，所以也可以做其他的操作，比如根据学生id更新学生名字
    @Query("update table_students_coroutine set name_ =:name where sid =:id")
    suspend fun updateStudentById(name: String, id: Long)

    //@Query参数可以是sql语句，所以也可以做其他的操作，比如根据学生id删除某个人
    @Query("delete from table_students_coroutine where sid =:id")
    suspend fun deleteStudentById(id: Long)

}