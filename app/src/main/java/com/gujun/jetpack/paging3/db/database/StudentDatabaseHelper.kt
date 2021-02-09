package com.gujun.jetpack.paging3.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gujun.jetpack.paging3.db.dao.StudentDao
import com.gujun.jetpack.paging3.db.entity.Student3

/**
 *    author : gujun
 *    date   : 2021/2/9 10:43
 *    desc   :
 */
@Database(entities = [Student3::class], version = 1, exportSchema = false)
abstract class StudentDatabaseHelper : RoomDatabase() {

    abstract fun getStudentDao(): StudentDao

    companion object {

        private var instance: StudentDatabaseHelper? = null

        fun getInstance(context: Context): StudentDatabaseHelper {
            if (instance == null)
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabaseHelper::class.java,
                    "student3.db"
                )
                    .allowMainThreadQueries()
                    .build()
            return instance!!
        }
    }
}