package com.gujun.jetpack.viewmodellivedatadatabindingroom.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.gujun.jetpack.viewmodellivedatadatabindingroom.entity.User

/**
 *    author : gujun
 *    date   : 2021/2/1 10:49
 *    desc   : 数据库操作（增、删、改、查）接口，@Insert、@Delete、@Update 和 @Query
 */

//添加Room中的@Dao注解
@Dao
interface UserDao {

    //@Query 查询注解，它的参数是String类型，我们直接写SQL语句进行执行
    @Query("select * from table_users where sid =:id")
    fun getUserById(id: Long): User

    //@Query 查询注解，它的参数是String类型，我们直接写SQL语句进行执行
    @Query("select * from table_users")
    fun getAllUser(): List<User>

    //插入数据，onConflict代表已经存在这个数据之后如何操作（OnConflictStrategy.REPLACE替换）
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    //删除某条数据
    @Delete
    fun deleteUser(user: User)

    //更新某条数据
    @Update
    fun updateUser(user: User)

    //@Query参数可以是sql语句，所以也可以做其他的操作，比如根据学生id更新学生名字
    @Query("update table_users set name_ =:name where sid =:id")
    fun updateUserById(name: String, id: Long)

    //@Query参数可以是sql语句，所以也可以做其他的操作，比如根据学生id删除某个人
    @Query("delete from table_users where sid =:id")
    fun deleteUserById(id: Long)

    //@Query 查询注解，它的参数是String类型，我们直接写SQL语句进行执行,ROOM支持返回LiveData数据
    //注意此处的返回一定是：LiveData！！子类不行，会导致编译不过
    @Query("select * from table_users")
    fun getAllUserLiveData(): LiveData<List<User>>

}