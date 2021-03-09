package com.gujun.jetpack.roomviewmodellivedatacoroutine

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
                        "app_coroutine_data.db" //数据库名称
                    )
                        .fallbackToDestructiveMigration()//数据库版本升级时调用的方法，当未匹配到版本的时候就会直接删除表然后重新创建,此时表为空
                        .build()
            }
            return instance as DatabaseHelper
        }
    }

}