package com.gujun.jetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 *    author : gujun
 *    date   : 2021/2/1 11:28
 *    desc   : 数据库生成类,抽象的
 */

//添加数据库注解,
// entities是这个db包含的实体表，
// version是数据库版本,
// exportSchema = true,在app的build.gradle文件中，配置room.schemaLocation选项为app/schemas，方便我们查看数据库架构信息。
@Database(entities = [Student::class], version = 1, exportSchema = true)
//继承RoomDatabase类
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun getStudentDao(): StudentDao

    companion object {

        private var instance: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseHelper::class.java,
                        "app_data.db" //数据库名称
                    ).allowMainThreadQueries() //允许操作数据库在主线程，如果不设置此方法，在主线程操作数据库会抛异常
                        .build()
            }
            return instance as DatabaseHelper
        }
    }

}