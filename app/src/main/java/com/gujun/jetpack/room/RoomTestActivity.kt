package com.gujun.jetpack.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gujun.jetpack.R
import kotlinx.android.synthetic.main.activity_room.*

/**
 *    author : gujun
 *    date   : 2021/1/27 10:45
 *    desc   : room操作数据库
 */
class RoomTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        addBtn.setOnClickListener { addMethod() }
        deleteBtn.setOnClickListener { deleteMethod() }
        updateBtn.setOnClickListener { updateMethod() }
        queryBtn.setOnClickListener { queryMethod() }

    }

    private fun addMethod() {

        for (i in 0..10) {
            val student = Student(sid = i.toLong(), name = "我是$i", age = 10)
            DatabaseHelper.getInstance(this).getStudentDao().addStudent(student)
        }

    }

    private fun deleteMethod() {
        DatabaseHelper.getInstance(this).getStudentDao().deleteStudentById(5)
    }

    private fun updateMethod() {
        DatabaseHelper.getInstance(this).getStudentDao().updateStudentById("我是2New", 2)
    }

    private fun queryMethod() {
        val stringBuilder = StringBuilder()
        DatabaseHelper.getInstance(this).getStudentDao().getAllStudent()
            .forEach { stringBuilder.append(it.name).append("\n") }
        content.text = stringBuilder
    }
}