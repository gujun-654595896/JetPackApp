package com.gujun.jetpack.paging.datasource

import androidx.paging.PositionalDataSource
import com.gujun.jetpack.paging.model.Student

/**
 *    author : gujun
 *    date   : 2021/2/5 14:05
 *    desc   : 获取数据的类，根据数据的起始位置、每次获取数据条数来进行分页加载
 */
class StudentPositionalDataSource : PositionalDataSource<Student>() {

    /**
     * 首次加载的数据的回调
     */
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Student>) {
        callback.onResult(loadData(0, params.requestedLoadSize), 0)
    }

    /**
     * 除了首次的加载数据的回调
     */
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Student>) {
        callback.onResult(loadData(params.startPosition, params.loadSize))
    }

    //加载数据的方法，根据数据索引位置获取数据
    private fun loadData(startPosition: Int, pageSize: Int): List<Student> {
        val studentList = ArrayList<Student>()
        for (i in startPosition until (startPosition + pageSize)) {
            studentList.add(Student(i.toString(), "我是：$i", 20))
        }
        return studentList
    }
}