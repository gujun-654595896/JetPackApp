package com.gujun.jetpack.livedata

import androidx.lifecycle.MutableLiveData

/**
 *    author : gujun
 *    date   : 2021/1/27 13:39
 *    desc   : 请求数据的类，当请求到结果后通知页面更新
 */
class LiveDataViewModel {

    private var mutableLiveData: MutableLiveData<String>? = null

    constructor() {
        mutableLiveData = MutableLiveData()
    }

    companion object {

        private var liveDataViewModel: LiveDataViewModel? = null

        fun getInstance(): LiveDataViewModel {
            if (liveDataViewModel == null) {
                liveDataViewModel = LiveDataViewModel()
            }
            return liveDataViewModel!!
        }
    }

    fun getMutableLiveData(): MutableLiveData<String> {
        return mutableLiveData!!
    }

    fun requestData() {
        //...做一些数据获取处理
        getMutableLiveData().value = "哈哈哈哈"
    }

}