package com.gujun.jetpack.livedata

import androidx.lifecycle.MutableLiveData

/**
 *    author : gujun
 *    date   : 2021/1/26 17:15
 *    desc   : LiveData管理类
 */
class DataManager {

    private var mutableLiveData: MutableLiveData<String>? = null

    init {
        mutableLiveData = MutableLiveData()
    }

    companion object {

        private var dataManager: DataManager? = null

        fun getInstance(): DataManager {
            if (dataManager == null) {
                dataManager = DataManager()
            }
            return dataManager!!
        }
    }

    fun getMutableLiveData(): MutableLiveData<String> {
        return mutableLiveData!!
    }

}