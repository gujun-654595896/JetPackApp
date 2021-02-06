package com.gujun.jetpack.paging.datasource

import androidx.paging.ItemKeyedDataSource
import androidx.paging.PositionalDataSource
import com.gujun.jetpack.paging.model.Student

/**
 *    author : gujun
 *    date   : 2021/2/5 14:05
 *    desc   : 获取数据的类，根据数据的起始位置、每次获取数据条数来进行分页加载
 */
class StudentItemKeyedDataSource : ItemKeyedDataSource<Int, Student>() {



    //加载数据的方法，根据数据索引位置获取数据
    private fun loadData(startPosition: Int, pageSize: Int): List<Student> {
        val studentList = ArrayList<Student>()
        for (i in startPosition until (startPosition + pageSize)) {
            studentList.add(Student(i.toString(), "我是：$i", 20))
        }
        return studentList
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Student>
    ) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Student>) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Student>) {
        TODO("Not yet implemented")
    }

    override fun getKey(item: Student): Int {
        TODO("Not yet implemented")
    }
}