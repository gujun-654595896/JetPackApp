package com.gujun.jetpack.livedata

import androidx.lifecycle.*

/**
 *    author : gujun
 *    date   : 2021/3/9 16:38
 *    desc   : LiveData的 Transformations.map & Transformations.switchMap 用法
 *
 *          Transformations.map从一个LiveData<X>转换到另外一个LiveData<Y>
 *          Transformations.switchMap用一个LiveData<X>的value改变来触发另外一个LiveData<Y>的获取
 */
class TestTransformation {

    private val oldLiveData: MutableLiveData<String> = MutableLiveData("1")

    companion object {

        private var instance: TestTransformation? = null

        fun getInstance(): TestTransformation {
            if (instance == null) {
                instance = TestTransformation()
            }
            return instance!!
        }
    }

    fun getOldLiveData(): MutableLiveData<String> {
        return oldLiveData
    }

    fun changeLiveDataMap(oldLiveData: MutableLiveData<String>): LiveData<String> {
        return Transformations.map(oldLiveData) {
            //只要oldLiveData的值修改就触发此处
            "change $it"
        }
    }

    /**
     * 此方式需要单独引入：implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
     */
    fun changeLiveDataMapNew(oldLiveData: MutableLiveData<String>): LiveData<String> {
        return oldLiveData.map {
            //只要oldLiveData的值修改就触发此处
            "change $it"
        }
    }


    fun changeLiveDataSwitchMap(oldLiveData: MutableLiveData<String>): LiveData<Boolean> {
        return Transformations.switchMap(oldLiveData) {
            //只要oldLiveData的值修改就触发此处
            when (it) {
                "1" -> MutableLiveData<Boolean>(false)
                "2" -> MutableLiveData<Boolean>(true)
                else -> {
                    MutableLiveData<Boolean>(true)
                }
            }
        }
    }


    /**
     * 此方式需要单独引入：implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
     */
    fun changeLiveDataSwitchMapNew(oldLiveData: MutableLiveData<String>): LiveData<Boolean> {
        return oldLiveData.switchMap {
            //只要oldLiveData的值修改就触发此处
            when (it) {
                "1" -> MutableLiveData<Boolean>(false)
                "2" -> MutableLiveData<Boolean>(true)
                else -> {
                    MutableLiveData<Boolean>(true)
                }
            }
        }
    }
}