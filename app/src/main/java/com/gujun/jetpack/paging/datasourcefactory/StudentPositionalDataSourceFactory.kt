package com.gujun.jetpack.paging.datasourcefactory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.gujun.jetpack.paging.datasource.StudentPositionalDataSource
import com.gujun.jetpack.paging.model.Student


/**
 *    author : gujun
 *    date   : 2021/2/5 14:29
 *    desc   : 生成DataSource对象的类
 */
class StudentPositionalDataSourceFactory : DataSource.Factory<Int, Student>() {

    private val liveData = MutableLiveData<StudentPositionalDataSource>()

    override fun create(): DataSource<Int, Student> {
        val studentPositionalDataSource = StudentPositionalDataSource()
        liveData.postValue(studentPositionalDataSource)
        return studentPositionalDataSource
    }

}