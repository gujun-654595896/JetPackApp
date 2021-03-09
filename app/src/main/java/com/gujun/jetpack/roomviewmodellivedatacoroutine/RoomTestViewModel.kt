package com.gujun.jetpack.roomviewmodellivedatacoroutine

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 *    author : gujun
 *    date   : 2021/3/9 13:33
 *    desc   :
 */
class RoomTestViewModel(val app: Application) : AndroidViewModel(app) {

    private val repository: StudentRepository = StudentRepository(DatabaseHelper.getInstance(app).getStudentDao())

    fun addStudent(student: Student) {
        viewModelScope.launch {
            repository.addStudent(student)
        }
    }

    fun updateStudentById(name: String, id: Long) {
        viewModelScope.launch {
            repository.updateStudentById(name, id)
        }
    }

    fun deleteStudentById(id: Long) {
        viewModelScope.launch {
            repository.deleteStudentById(id)
        }
    }

    fun getAllStudent(): LiveData<List<Student>> {
        return repository.getAllStudent()
    }
}