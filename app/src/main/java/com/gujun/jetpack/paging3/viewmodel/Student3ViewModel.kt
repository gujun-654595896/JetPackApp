package com.gujun.jetpack.paging3.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.recyclerview.widget.DiffUtil
import com.gujun.jetpack.BR
import com.gujun.jetpack.R
import com.gujun.jetpack.paging3.adapter.base.DataBindingPagingAdapter
import com.gujun.jetpack.paging3.datasource.Paging3DataSource
import com.gujun.jetpack.paging3.db.entity.Student3

/**
 *    author : gujun
 *    date   : 2021/2/9 11:20
 *    desc   :
 */
class Student3ViewModel(val app: Application) : AndroidViewModel(app) {

    private var adapter: DataBindingPagingAdapter<Student3>

    fun getData() = Pager(PagingConfig(20, 10, true, 20), initialKey = 0) {
        Paging3DataSource(app)
    }
        .flow//Paging3使用flow传递数据
        .cachedIn(viewModelScope)//绑定协程生命周期，必须加上，否则可能崩溃
        .asLiveData(viewModelScope.coroutineContext)//使用LiveData

    /**
     * 用于计算列表中两个非空项之间的差异的回调。
     *
     * 之前数据更新了，需要通过notifyDataSetChanged()通知整个RecyclerView，效率不高
     * 使用DiffUtil只会更新需要更新的Item，不需要刷新整个RecyclerView，并且可以在Item删除的时候加上动画效果
     */
    private var diffCallback: DiffUtil.ItemCallback<Student3>

    init {
        diffCallback =
            object : DiffUtil.ItemCallback<Student3>() {
                /**
                 * 当DiffUtil想要检测两个对象是否代表同一个Item时，调用该方法进行判断
                 */
                override fun areItemsTheSame(oldItem: Student3, newItem: Student3): Boolean {
                    return oldItem.id == newItem.id
                }

                /**
                 * 当DiffUtil想要检测两个Item是否有一样的数据时，调用该方法进行判断
                 *
                 * 内容如果更新了，展示给用户看的东西可能也需要更新，所以需要这个判断
                 */
                override fun areContentsTheSame(oldItem: Student3, newItem: Student3): Boolean {
                    return oldItem == newItem
                }
            }

        adapter = DataBindingPagingAdapter(
            app,
            R.layout.layout_paging3_item,
            BR.student,
            BR.onItemClickListener,
            diffCallback
        )
    }

    fun getAdapter(): DataBindingPagingAdapter<Student3> {
        return adapter
    }

}