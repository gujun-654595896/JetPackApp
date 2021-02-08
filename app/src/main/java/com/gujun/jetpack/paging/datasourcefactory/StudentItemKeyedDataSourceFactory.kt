package com.gujun.jetpack.paging.datasourcefactory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.gujun.jetpack.paging.datasource.StudentItemKeyedDataSource
import com.gujun.jetpack.paging.model.Student


/**
 *    author : gujun
 *    date   : 2021/2/5 14:29
 *    desc   : 生成DataSource对象的类
 */
class StudentItemKeyedDataSourceFactory : DataSource.Factory<String, Student>() {

    private val liveData = MutableLiveData<StudentItemKeyedDataSource>()

    override fun create(): DataSource<String, Student> {
        val itemKeyedDataSource = StudentItemKeyedDataSource()
        liveData.postValue(itemKeyedDataSource)
        return itemKeyedDataSource
    }

}