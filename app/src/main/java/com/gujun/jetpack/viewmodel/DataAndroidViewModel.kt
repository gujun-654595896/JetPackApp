package com.gujun.jetpack.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.gujun.jetpack.R

/**
 *    author : gujun
 *    date   : 2021/1/28 11:08
 *    desc   : 使用ViewModel的优点：页面数据的管理者，此类中的数据不会丢失（例如：页面横竖屏切换生命周期重新执行后数据还保留）
 *    AndroidViewModel是给需要application对象使用的，ViewModel是给不需要application使用的
 */
class DataAndroidViewModel(application: Application) : AndroidViewModel(application) {

    var number: Int = 0

    fun useApplication() {
        val appName = getApplication<Application>().getString(R.string.app_name)
        Log.e("DataAndroidViewModel", "useApplication: $appName")
    }

}