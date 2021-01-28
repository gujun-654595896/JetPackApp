package com.gujun.jetpack.viewmodel

import androidx.lifecycle.ViewModel

/**
 *    author : gujun
 *    date   : 2021/1/28 11:08
 *    desc   : 使用ViewModel的优点：页面数据的管理者，此类中的数据不会丢失（例如：页面横竖屏切换生命周期重新执行后数据还保留）
 */
class DataViewModel : ViewModel() {

    var number: Int = 0

}