package com.gujun.jetpack.paging3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.gujun.jetpack.paging3.adapter.base.holder.DataBindingViewHolder

/**
 *    author : gujun
 *    date   : 2021/2/9 16:12
 *    desc   : Header的适配器
 */
class LoadStateHeaderAdapter<T>(
    private val layoutId: Int,
    private val itemBRId: Int,
    private val stateBRId: Int,
    private val clickListenerVariableId: Int = 0,
    private var data: T?
) : LoadStateAdapter<DataBindingViewHolder>() {

    private var l: OnHeaderViewClickListener<T>? = null

    fun setOnItemViewClickListener(listener: OnHeaderViewClickListener<T>) {
        l = listener
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, loadState: LoadState) {
        holder.getBinding()!!.setVariable(itemBRId, data)
        holder.getBinding()!!.setVariable(stateBRId, loadState)
        if (l != null) holder.getBinding()!!.setVariable(clickListenerVariableId, l)
        holder.getBinding()!!.executePendingBindings()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): DataBindingViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
        val viewHolder =
            DataBindingViewHolder(
                binding.root
            )
        viewHolder.setBinding(binding)
        return viewHolder
    }

    override fun getStateViewType(loadState: LoadState): Int {
        return super.getStateViewType(loadState)
    }

    /**
     * 此方法是关键，返回值决定什么时候显示
     */
    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return true
    }

    interface OnHeaderViewClickListener<T> {
        fun onViewClick(v: View?, program: T)
    }

}