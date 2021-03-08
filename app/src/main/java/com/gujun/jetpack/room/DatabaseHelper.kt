package com.gujun.jetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 *    author : gujun
 *    date   : 2021/2/1 11:28
 *    desc   : 数据库生成类,抽象的
 */

//添加数据库注解,
// entities是这个db包含的实体表，
// version是数据库版本,
// exportSchema = true,在app的build.gradle文件中，配置room.schemaLocation选项为app/schemas，方便我们查看数据库架构信息。
@Database(entities = [Student::class], version = 13, exportSchema = true)
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
//                        .fallbackToDestructiveMigration()//数据库版本升级时调用的方法，当未匹配到版本的时候就会直接删除表然后重新创建,此时表为空
//                        .fallbackToDestructiveMigrationOnDowngrade()//数据库版本降级时调用的方法，当未匹配到版本的时候就会直接删除表然后重新创建,此时表为空
//                        .fallbackToDestructiveMigrationFrom(4)//特定的数据库版本，当未匹配到版本的时候就会直接删除表然后重新创建,此时表为空
                        .addMigrations(object :Migration(12,13){
                            override fun migrate(database: SupportSQLiteDatabase) {
                                //1、如果升级数据库版本没有改变数据库代码可以此处不添加代码

                                //2、如果有数据库修改就需要植入SQL语句
                                //2.1简单升级，添加某一列数据，注意：和自己创建的Entity新添加的字段DEFAULT值要一样，否则报错
                                //此种方式存在弊端：多版本数据库增量迁移会涉及到添加很多迁移策略，很麻烦还容易出错
//                                database.execSQL("ALTER TABLE table_students ADD COLUMN address TEXT NOT NULL DEFAULT '34'")

                                //2.2数据库结构修改复杂时增量迁移
                                //  创建新的临时表
                                database.execSQL( "CREATE TABLE table_students_new (sid INTEGER NOT NULL, name_ TEXT NOT NULL, age INTEGER NOT NULL DEFAULT 0 , PRIMARY KEY(sid))" )
                                // 复制数据
                                database.execSQL( "INSERT INTO table_students_new (sid, name_, age) SELECT sid, name_, age FROM table_students" )
                                // 删除表结构
                                database.execSQL( "DROP TABLE table_students" )
                                // 临时表名称更改
                                database.execSQL( "ALTER TABLE table_students_new RENAME TO table_students" )
                            }
                        })
                        .build()
            }
            return instance as DatabaseHelper
        }
    }

}