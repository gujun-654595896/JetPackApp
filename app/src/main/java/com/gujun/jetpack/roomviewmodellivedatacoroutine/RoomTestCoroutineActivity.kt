package com.gujun.jetpack.roomviewmodellivedatacoroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gujun.jetpack.R
import kotlinx.android.synthetic.main.activity_room.*

/**
 *    author : gujun
 *    date   : 2021/1/27 10:45
 *    desc   : room操作数据库,支持协程操作
 */
class RoomTestCoroutineActivity : AppCompatActivity() {
    private lateinit var dataViewModelAndroid: RoomTestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        dataViewModelAndroid = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        ).get(RoomTestViewModel::class.java)

        addBtn.setOnClickListener { addMethod() }
        deleteBtn.setOnClickListener { deleteMethod() }
        updateBtn.setOnClickListener { updateMethod() }
        queryBtn.setOnClickListener { queryMethod() }

    }

    private fun addMethod() {

        for (i in 0..10) {
            val student =
                Student(sid = i.toLong(), name = "我是$i", age = 10)
            dataViewModelAndroid.addStudent(student)
        }

    }

    private fun deleteMethod() {
        dataViewModelAndroid.deleteStudentById(5)
    }

    private fun updateMethod() {
        dataViewModelAndroid.updateStudentById("我是2New", 2)
    }

    private fun queryMethod() {
        dataViewModelAndroid.getAllStudent().observe(this, Observer<List<Student>> {
            val stringBuilder = StringBuilder()
            it.forEach { item -> stringBuilder.append(item.name).append("\n") }
            content.text = stringBuilder
        })
    }
}