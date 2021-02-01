package com.gujun.jetpack.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 *    author : gujun
 *    date   : 2021/2/1 10:14
 *    desc   : 数据库对象实体类
 */
// tableName用来指定表的名称
@Entity(tableName = "table_students")
data class Student(

    //PrimaryKey用来指定主键，autoGenerate是否是自增长
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo
    var sid: Long,

    //ColumnInfo指定表中列的信息，name = "name_"用来指定在数据库中列名，不指定就是默认的属性名
    @ColumnInfo(name = "name_")
    var name: String,

    //ColumnInfo指定表中列的信息，defaultValue = "0"用来指定默认值
    @ColumnInfo(defaultValue = "0")
    var age: Int

)