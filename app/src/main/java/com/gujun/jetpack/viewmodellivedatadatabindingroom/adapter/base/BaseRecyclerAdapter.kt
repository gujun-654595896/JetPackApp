package com.gujun.jetpack.viewmodellivedatadatabindingroom.adapter.base

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.*

/**
 *    author : gujun
 *    date   : 2021/1/6 14:39
 *    desc   : RecyclerView的Adapter基类
 */
abstract class BaseRecyclerAdapter<T>(context: Context?) :
    RecyclerView.Adapter<ViewHolder?>() {
    private var mItems: MutableList<T?>?
    private var mContext: Context? = null

    fun getList(): List<T?>? {
        return mItems
    }

    fun addItem(item: T) {
        addItem(mItems!!.size, item)
    }

    fun addItem(index: Int, item: T?) {
        if (item == null) return
        mItems!!.add(index, item)
        notifyItemRangeInserted(index, 1)
        notifyItemRangeChanged(index, mItems!!.size - index)
    }

    fun addItems(items: List<T>?) {
        if (items == null) return
        val position = mItems!!.size
        mItems!!.addAll(items)
        notifyItemRangeInserted(position, items.size)
        notifyItemRangeChanged(position, items.size)
    }

    fun addItems(index: Int, items: List<T>?) {
        if (items == null || items.size <= 0) return
        mItems!!.addAll(index, items)
        notifyItemRangeInserted(index, items.size)
        notifyItemRangeChanged(index, items.size)
    }

    fun containsAll(items: List<T>?): Boolean {
        return mItems!!.containsAll(items!!)
    }

    fun updateItem(tasks: T?, position: Int) {
        if (tasks == null) return
        mItems!![position] = tasks
        notifyItemRangeChanged(position, 1)
    }

    fun updateItems(items: List<T>?) {
        if (items == null) return
        mItems!!.clear()
        mItems!!.addAll(items)
        notifyDataSetChanged()
    }

    fun indexOf(item: T?): Int {
        return if (item == null || mItems == null || mItems!!.size <= 0) -1 else mItems!!.indexOf(
            item
        )
    }

    fun removeItemNotRange(index: Int) {
        mItems!!.removeAt(index)
        notifyItemRangeRemoved(index, 1)
    }

    fun removeItem(index: Int) {
        mItems!!.removeAt(index)
        notifyItemRangeRemoved(index, 1)
        notifyItemRangeChanged(index, mItems!!.size - index)
    }

    fun removeItems(fromIndex: Int) {
        val mItemsNew = mItems!!.subList(0, fromIndex)
        notifyItemRangeRemoved(fromIndex, mItems!!.size - fromIndex)
        notifyItemRangeChanged(fromIndex, mItems!!.size - fromIndex)
        mItems = mItemsNew
    }

    fun removeItemsFromTop(toIndex: Int) {
        val mItemsNew = mItems!!.subList(toIndex, mItems!!.size)
        notifyItemRangeRemoved(0, toIndex)
        mItems = mItemsNew
    }

    fun removeAllItems() {
        mItems!!.clear()
        notifyDataSetChanged()
    }

    fun moveItem(item: T, fromPosition: Int, toPosition: Int) {
        if (fromPosition > toPosition) {
            Collections.rotate(mItems!!.subList(toPosition, fromPosition + 1), 1)
        } else {
            Collections.rotate(mItems!!.subList(fromPosition, toPosition + 1), -1)
        }
        notifyItemMoved(fromPosition, toPosition)
        mItems!![toPosition] = item
        notifyItemChanged(toPosition)
    }

    fun getItem(location: Int): T? {
        return mItems!![location]
    }

    override fun getItemCount(): Int {
        return if (mItems == null) 0 else mItems!!.size
    }

    init {
        mContext = context
        mItems = ArrayList()
    }
}