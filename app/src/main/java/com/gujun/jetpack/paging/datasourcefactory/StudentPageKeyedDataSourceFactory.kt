package com.gujun.jetpack.paging.datasourcefactory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.gujun.jetpack.paging.datasource.StudentPageKeyedDataSource
import com.gujun.jetpack.paging.model.Student


/**
 *    author : gujun
 *    date   : 2021/2/5 14:29
 *    desc   : 生成DataSource对象的类
 */
class StudentPageKeyedDataSourceFactory : DataSource.Factory<Int, Student>() {

    private val liveData = MutableLiveData<StudentPageKeyedDataSource>()

    override fun create(): DataSource<Int, Student> {
        val studentPageKeyedDataSource = StudentPageKeyedDataSource()
        liveData.postValue(studentPageKeyedDataSource)
        return studentPageKeyedDataSource
    }

}