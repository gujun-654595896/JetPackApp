package com.gujun.jetpack.paging3.adapter.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gujun.jetpack.viewmodellivedatadatabindingroom.adapter.base.holder.DataBindingViewHolder

/**
 *    author : gujun
 *    date   : 2021/1/6 14:40
 *    desc   : databinding 关联Recycler的基类Adapter
 */
class DataBindingPagingAdapter<T : Any>(diffCallback: DiffUtil.ItemCallback<T>) :
    PagingDataAdapter<T, DataBindingViewHolder>(diffCallback) {
    private var layoutId = 0
    private var itemBRId = 0
    private var clickListenerVariableId = 0
    private var l: OnItemViewClickListener<T>? = null

    constructor(
        context: Context?,
        layoutId: Int,
        itemBRId: Int,
        clickListenerVariableId: Int,
        diffCallback: DiffUtil.ItemCallback<T>
    ) : this(diffCallback) {
        this.layoutId = layoutId
        this.itemBRId = itemBRId
        this.clickListenerVariableId = clickListenerVariableId
    }

    fun setOnItemViewClickListener(listener: OnItemViewClickListener<T>) {
        l = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBindingViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
        val viewHolder = DataBindingViewHolder(binding.root)
        viewHolder.setBinding(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        val videoListViewHolder = holder as DataBindingViewHolder
        videoListViewHolder.getBinding()!!.setVariable(itemBRId, getItem(position))
        if (l != null) videoListViewHolder.getBinding()!!.setVariable(clickListenerVariableId, l)
        videoListViewHolder.getBinding()!!.executePendingBindings()
    }

    interface OnItemViewClickListener<T> {
        fun onViewClick(v: View?, program: T)
    }
}
