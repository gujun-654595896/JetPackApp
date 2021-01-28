package com.gujun.jetpack.viewmodellivedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *    author : gujun
 *    date   : 2021/1/28 16:37
 *    desc   : ViewModel + LiveData结合使用，保证既能统一管理数据并且页面旋转数据还保存  还能  动态监听数据变化统一修改视图
 */
class DataViewModelLiveData : ViewModel() {

    private var liveData: MutableLiveData<Int> = MutableLiveData()

    init {
        liveData.value = 0
    }

    fun getLiveData(): MutableLiveData<Int> {
        return liveData
    }

    fun setNumber(number: Int) {
        liveData.value = number
    }

    fun getNumber(): Int {
        return liveData.value!!
    }

}