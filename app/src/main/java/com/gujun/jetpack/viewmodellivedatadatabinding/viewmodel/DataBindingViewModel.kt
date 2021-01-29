package com.gujun.jetpack.viewmodellivedatadatabinding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataBindingViewModel : ViewModel() {

    private var liveData: MutableLiveData<String> = MutableLiveData()

    init {
        liveData.value = "拼接后的数据："
    }

    fun getLiveData(): MutableLiveData<String> {
        return liveData
    }

    fun appendStr(str: String) {
        liveData.value = liveData.value + str
    }
}