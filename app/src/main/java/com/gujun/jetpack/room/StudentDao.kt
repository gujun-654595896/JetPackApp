package com.gujun.jetpack.room

import androidx.room.*

/**
 *    author : gujun
 *    date   : 2021/2/1 10:49
 *    desc   : 数据库操作（增、删、改、查）接口，@Insert、@Delete、@Update 和 @Query
 */

//添加Room中的@Dao注解
@Dao
interface StudentDao {

    //@Query 查询注解，它的参数是String类型，我们直接写SQL语句进行执行
    @Query("select * from table_students where sid =:id")
    fun getStudentById(id: Long): Student

    //@Query 查询注解，它的参数是String类型，我们直接写SQL语句进行执行
    @Query("select * from table_students")
    fun getAllStudent(): List<Student>

    //插入数据，onConflict代表已经存在这个数据之后如何操作（OnConflictStrategy.REPLACE替换）
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStudent(student: Student)

    //删除某条数据
    @Delete
    fun deleteStudent(student: Student)

    //更新某条数据
    @Update
    fun updateStudent(student: Student)

    //@Query参数可以是sql语句，所以也可以做其他的操作，比如根据学生id更新学生名字
    @Query("update table_students set name_ =:name where sid =:id")
    fun updateStudentById(name: String, id: Long)

    //@Query参数可以是sql语句，所以也可以做其他的操作，比如根据学生id删除某个人
    @Query("delete from table_students where sid =:id")
    fun deleteStudentById(id: Long)

}