package com.gujun.jetpack.paging.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import com.gujun.jetpack.BR
import com.gujun.jetpack.R
import com.gujun.jetpack.paging.adapter.base.DataBindingPagingAdapter
import com.gujun.jetpack.paging.datasourcefactory.StudentItemKeyedDataSourceFactory
import com.gujun.jetpack.paging.datasourcefactory.StudentPageKeyedDataSourceFactory
import com.gujun.jetpack.paging.datasourcefactory.StudentPositionalDataSourceFactory
import com.gujun.jetpack.paging.model.Student

/**
 *    author : gujun
 *    date   : 2021/2/5 14:37
 *    desc   : ViewModel类
 */
private const val PageSize = 75

class StudentItemKeyedViewModel(app: Application) : AndroidViewModel(app) {

    private var liveData: LiveData<PagedList<Student>>
    private var adapter: DataBindingPagingAdapter<Student>

    /**
     * 用于计算列表中两个非空项之间的差异的回调。
     *
     * 之前数据更新了，需要通过notifyDataSetChanged()通知整个RecyclerView，效率不高
     * 使用DiffUtil只会更新需要更新的Item，不需要刷新整个RecyclerView，并且可以在Item删除的时候加上动画效果
     */
    private var diffCallback: DiffUtil.ItemCallback<Student>

    init {
        StudentItemKeyedDataSourceFactory().apply {

            val config =
                PagedList.Config.Builder() // 是否显示占位。默认为true。当被设置为true时，要求在DataSource中提供数据源的总量，否则会报错。
                    // 这是因为RecyclerView需要知道数据总量，为这些数据预留位置
                    .setEnablePlaceholders(false)
                    //设置每页加载的数量
                    .setPageSize(PageSize)
                    //设置距离底部还有多少条数据时加载下一页数据
                    .setPrefetchDistance(10)
                    //重点，这里需要设置预获取的值">0"，否则getKey()和loadBefore()以及loadAfter()不会被调用
                    //首次初始化加载的数量，需是分页加载数量的倍数。若没有设置，则默认是PER_PAGE的三倍
                    .setInitialLoadSizeHint(PageSize)
                    //好像没有效果
                    //设置PagedList所能承受的最大数量，一般来说是PER_PAGE的许多许多倍，超过可能会出现异常。
                    .setMaxSize(Int.MAX_VALUE)
                    .build()

            //通过LivePagedListBuilder创建LiveData<PagedList<Student>>
            liveData = LivePagedListBuilder(this, config).build()

            diffCallback =
                object : DiffUtil.ItemCallback<Student>() {
                    /**
                     * 当DiffUtil想要检测两个对象是否代表同一个Item时，调用该方法进行判断
                     */
                    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                        return oldItem.id == newItem.id
                    }

                    /**
                     * 当DiffUtil想要检测两个Item是否有一样的数据时，调用该方法进行判断
                     *
                     * 内容如果更新了，展示给用户看的东西可能也需要更新，所以需要这个判断
                     */
                    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                        return oldItem == newItem
                    }
                }

            adapter = DataBindingPagingAdapter(
                app,
                R.layout.layout_paging_item,
                BR.student,
                BR.onItemClickListener,
                diffCallback
            )
        }
    }

    fun getAdapter(): DataBindingPagingAdapter<Student> {
        return adapter
    }

    fun getDataList(): LiveData<PagedList<Student>> {
        return liveData
    }

}