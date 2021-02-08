package com.gujun.jetpack.paging.datasource

import androidx.paging.PageKeyedDataSource
import com.gujun.jetpack.paging.model.Student

/**
 *    author : gujun
 *    date   : 2021/2/5 14:05
 *    desc   : 获取数据的类，根据每页关键key(非常常见的是key作为请求的page的大小)、一页多少条数据来进行分页加载的
 *    其实此种方式针对每页数据可以更灵活，就是根据上下页的key获取对应的数据添加上
 */
class StudentPageKeyedDataSource : PageKeyedDataSource<Int, Student>() {

    private val startPageIndex = 5

    override fun loadInitial(
        params: PageKeyedDataSource.LoadInitialParams<Int>,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, Student>
    ) {
        callback.onResult(
            loadData(startPageIndex, params.requestedLoadSize),
            startPageIndex - 1,
            startPageIndex + 1
        )
    }

    override fun loadAfter(
        params: PageKeyedDataSource.LoadParams<Int>,
        callback: PageKeyedDataSource.LoadCallback<Int, Student>
    ) {
        //params.key是当前要请求的key,callback.onResult中的adjacentPageKey是下一页需要的key
        callback.onResult(loadData(params.key, params.requestedLoadSize), params.key + 1)
    }

    override fun loadBefore(
        params: PageKeyedDataSource.LoadParams<Int>,
        callback: PageKeyedDataSource.LoadCallback<Int, Student>
    ) {
        //params.key是当前要请求的key,callback.onResult中的adjacentPageKey是上一页需要的key
        callback.onResult(loadData(params.key, params.requestedLoadSize), params.key - 1)
    }

    //加载数据的方法,按顺序加载数据,pageIndex：传进来的第几页，pageSize：传进来的每页数量
    private fun loadData(pageIndex: Int, pageSize: Int): List<Student> {
        val studentList = ArrayList<Student>()
        for (i in (pageIndex * pageSize) until (pageIndex * pageSize + pageSize)) {
            studentList.add(Student(i.toString(), "我是：$i", 20))
        }
        return studentList
    }

    //灵活加载数据的方式,key：获取当前页数据的关键信息，pageSize：传进来的每页数量
    private fun loadKeyData(key: Int, pageSize: Int): List<Student> {
        val studentList = ArrayList<Student>()
        for (i in (key * 5) until (key * 5 + pageSize)) {
            studentList.add(Student(i.toString(), "我是：$i", 20))
        }
        return studentList
    }
}