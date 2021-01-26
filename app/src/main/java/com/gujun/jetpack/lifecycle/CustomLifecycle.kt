package com.gujun.jetpack.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 *    author : gujun
 *    date   : 2021/1/26 15:49
 *    desc   : Activity/Fragment生命周期监听,需要在被监听的页面添加：lifecycle.addObserver(CustomLifecycle())
 *    适用于 : 好多页面的同一个生命周期执行同样的外部逻辑
 */
class CustomLifecycle : LifecycleObserver {
    private val TAG = "CustomLifecycle"

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public fun onCreateMethod() {
        Log.e(TAG, "onCreateMethod: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public fun onResumeMethod() {
        Log.e(TAG, "onResumeMethod: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public fun onDestroyMethod() {
        Log.e(TAG, "onDestroyMethod: ")
    }


}