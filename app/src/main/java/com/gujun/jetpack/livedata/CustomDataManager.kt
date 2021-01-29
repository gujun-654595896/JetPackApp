package com.gujun.jetpack.livedata

/**
 *    author : gujun
 *    date   : 2021/1/26 17:15
 *    desc   : LiveData管理类
 */
class CustomDataManager {

    private var mutableLiveData: CustomLiveData<String>? = null

    init {
        mutableLiveData = CustomLiveData()
    }

    companion object {

        private var dataManager: CustomDataManager? = null

        fun getInstance(): CustomDataManager {
            if (dataManager == null) {
                dataManager = CustomDataManager()
            }
            return dataManager!!
        }
    }

    fun getMutableLiveData(): CustomLiveData<String> {
        return mutableLiveData!!
    }

}