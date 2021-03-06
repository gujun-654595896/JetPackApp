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
 *
 *    跨版本升级说明：
 *      如果你的APP数据库版本是1，而最新的APP的数据库版本是4，那么在App升级时会顺序执行MIGRATION_1_2，MIGRATION_2_3，MIGRATION_3_4
 *      如果你的APP数据库版本是2，而最新的APP的数据库版本是4，那么在App升级时会顺序执行MIGRATION_2_3，MIGRATION_3_4
 */

//添加数据库注解,
// entities是这个db包含的实体表，
// version是数据库版本,
// exportSchema = true,在app的build.gradle文件中，配置room.schemaLocation选项为app/schemas，方便我们查看数据库架构信息。
@Database(entities = [Student::class], version = 4, exportSchema = true)
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
                        .fallbackToDestructiveMigration()//数据库版本升级时调用的方法，当未匹配到版本的时候就会直接删除表然后重新创建,此时表为空
//                        .fallbackToDestructiveMigrationOnDowngrade()//数据库版本降级时调用的方法，当未匹配到版本的时候就会直接删除表然后重新创建,此时表为空
//                        .fallbackToDestructiveMigrationFrom(4)//特定的数据库版本，当未匹配到版本的时候就会直接删除表然后重新创建,此时表为空
                        .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                         //数据库回调,可以监听数据库的创建，数据库的打开，数据库被破坏性迁移
                        .addCallback(DbCallback)
                        .build()
            }
            return instance as DatabaseHelper
        }

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE table_students ADD COLUMN address TEXT NOT NULL DEFAULT '123'")
            }
        }

        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE table_students ADD COLUMN address_ TEXT NOT NULL DEFAULT '123'")
            }
        }

        val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE table_students ADD COLUMN address__ TEXT NOT NULL DEFAULT '123'")
            }
        }
    }

    object DbCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // Called when the database is created for the first time. This is called after all the tables are created.
            // 在第一次创建数据库时调用。在创建所有表之后调用这个函数。
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            // Called when the database has been opened.
            // 当数据库被打开时调用
        }

        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
            super.onDestructiveMigration(db)
            // Called after the database was destructively migrated
            // 在数据库被破坏性迁移后调用
        }
    }

}