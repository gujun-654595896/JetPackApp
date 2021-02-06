package com.gujun.jetpack.paging.adapter.base.holder

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView.ViewHolder

/**
 *    author : gujun
 *    date   : 2021/1/6 14:38
 *    desc   : DataBinding Adapter相关的holder
 */
class DataBindingViewHolder(itemView: View?) : ViewHolder(itemView!!) {
    private var binding: ViewDataBinding? = null
    fun setBinding(binding: ViewDataBinding?) {
        this.binding = binding
    }

    fun getBinding(): ViewDataBinding? {
        return binding
    }
}