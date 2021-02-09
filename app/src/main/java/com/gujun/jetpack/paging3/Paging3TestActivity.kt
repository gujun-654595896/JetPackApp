package com.gujun.jetpack.paging3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gujun.jetpack.R
import com.gujun.jetpack.paging3.db.database.StudentDatabaseHelper
import com.gujun.jetpack.paging3.db.entity.Student3
import kotlinx.android.synthetic.main.activity_paging3_test.*
import kotlin.concurrent.thread

/**
 *    author : gujun
 *    date   : 2021/2/5 13:34
 *    desc   :
 */
class Paging3TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging3_test)
        toPage3.setOnClickListener {
            startActivity(Intent(this, Paging3DataSourceActivity::class.java))
        }

        addData.setOnClickListener {
            thread {
                for (i in 0..100) {
                    StudentDatabaseHelper.getInstance(this).getStudentDao()
                        .addStudent(Student3(i.toString(), "我是$i", 20))
                }
            }
        }
    }

}