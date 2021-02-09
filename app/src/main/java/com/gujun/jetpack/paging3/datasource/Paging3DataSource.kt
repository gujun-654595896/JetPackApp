package com.gujun.jetpack.paging3.datasource

import android.content.Context
import androidx.paging.PagingSource
import com.gujun.jetpack.paging3.db.database.StudentDatabaseHelper
import com.gujun.jetpack.paging3.db.entity.Student3

/**
 *    author : gujun
 *    date   : 2021/2/8 17:18
 *    desc   : 这里需要提醒的是如果你使用的不是Kotlin 协程而是Java，
 *    则需要继承对应的PagingSource如RxPagingSource或ListenableFuturePagingSource。
 *
 */
class Paging3DataSource(val context: Context) : PagingSource<Int, Student3>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Student3> {
        //我们主要的请求操作就在load方法中完成
        try {

            val totalList =
                StudentDatabaseHelper.getInstance(context).getStudentDao().getAllStudent()

            //当前页页码
            var currentPage = params.key ?: 1
            //仓库层请求数据
            var reqData = loadData(currentPage, params.loadSize, totalList)
            //下一页页码
            var nextPage =
                if (currentPage < (totalList.size / params.loadSize + (if (totalList.size % params.loadSize > 0) 1 else 0)) - 1) {
                    currentPage + 1
                } else {
                    null
                }
            //data ：返回的数据列表
            //prevKey ：上一页的key （传 null 表示没有上一页）
            //nextKey ：下一页的key （传 null 表示没有下一页）
            return LoadResult.Page(
                data = reqData,
                prevKey = null,
                nextKey = nextPage
            )

        } catch (e: Exception) {
            return LoadResult.Error(throwable = e)
        }
    }

    //加载数据的方法，根据数据索引位置获取数据
    private fun loadData(pageIndex: Int, pageSize: Int, totalList: List<Student3>): List<Student3> {
        val studentList = ArrayList<Student3>()
        for (i in totalList.indices) {
            if (i >= pageIndex * pageSize && i < pageIndex * pageSize + pageSize)
                studentList.add(totalList[i])
        }
        return studentList
    }


}