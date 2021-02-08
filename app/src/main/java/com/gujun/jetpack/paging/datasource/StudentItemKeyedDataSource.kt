package com.gujun.jetpack.paging.datasource

import androidx.paging.ItemKeyedDataSource
import com.gujun.jetpack.paging.model.Student

/**
 *    author : gujun
 *    date   : 2021/2/5 14:05
 *    desc   : 获取数据的类，根据上一页最后一条数据的key,往后取多少条数据
 */
class StudentItemKeyedDataSource : ItemKeyedDataSource<String, Student>() {
    private val totalList = ArrayList<Student>()

    init {
        for (i in 0 until 500) {
            totalList.add(Student(i.toString(), "我是：$i", 20))
        }
    }

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<Student>
    ) {
        callback.onResult(loadData("", params.requestedLoadSize))
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<Student>) {
        //params.key是当前页面需要的key
        if (params.key == totalList[totalList.size - 1].id) return
        callback.onResult(loadData(params.key, params.requestedLoadSize))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<Student>) {
        //注意此方式不适合向前加载分页，因为给的都是最后一条数据
    }

    override fun getKey(item: Student): String {
        //item是每次需要加载上一页或下一页的最后一条数据
        return item.id
    }

    //加载数据的方法，根据数据索引位置获取数据
    private fun loadData(key: String, pageSize: Int): List<Student> {
        var startIndex = 0
        var endIndex = startIndex + pageSize
        for (i in 0 until totalList.size) {
            if (totalList[i].id == key) {
                startIndex = i
                endIndex = startIndex + pageSize
                break
            }
        }
        endIndex = if (endIndex > totalList.size) totalList.size else endIndex
        if (startIndex == endIndex) {
            return ArrayList()
        }
        return totalList.subList(
            startIndex,
            endIndex
        )
    }

}