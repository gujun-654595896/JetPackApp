package com.gujun.jetpack.livedata

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;


/**
 *    author : gujun
 *    date   : 2021/1/29 17:15
 *    desc   : 自定义LiveData,使得LiveData数据不具有粘性，就是设置观察者前设置的数据，设置观察者后还能收到数据通知的问题
 */
class CustomLiveData<T> : MutableLiveData<T>() {

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
        hookMethod(observer)
    }


    private fun hookMethod(observer: Observer<in T>) {
        val liveDataClass = LiveData::class.java
        try {
            val mObservers: Field = liveDataClass.getDeclaredField("mObservers")
            mObservers.isAccessible = true

            // 获取集合 SafeIterableMap
            val observers: Any = mObservers.get(this)
            val observersClass: Class<*> = observers.javaClass

            // 获取SafeIterableMap的get(Object obj)方法
            val methodGet: Method = observersClass.getDeclaredMethod("get", Any::class.java)
            methodGet.isAccessible = true

            // 执行get函数
            val objectWrapperEntry: Any = methodGet.invoke(observers, observer)
            var objectWrapper: Any? = null
            if (objectWrapperEntry is Map.Entry<*, *>) {
                objectWrapper = objectWrapperEntry.value
            }
            if (objectWrapper == null) {
                throw NullPointerException("ObserverWrapper can not be null")
            }

            // 获取ObserverWrapper的Class对象  LifecycleBoundObserver extends ObserverWrapper
            val wrapperClass: Class<*> = objectWrapper.javaClass.superclass

            // 获取ObserverWrapper的field mLastVersion
            val mLastVersion: Field = wrapperClass.getDeclaredField("mLastVersion")
            mLastVersion.isAccessible = true

            // 获取liveData的field mVersion
            val mVersion: Field = liveDataClass.getDeclaredField("mVersion")
            mVersion.isAccessible = true
            val mV: Any = mVersion.get(this)

            // TODO 最关键的一句话  让我们的条件相等
            // 把当前ListData的mVersion赋值给 ObserverWrapper的field mLastVersion
            mLastVersion.set(objectWrapper, mV)
            mObservers.isAccessible = false
            methodGet.isAccessible = false
            mLastVersion.isAccessible = false
            mVersion.isAccessible = false
        } catch (e: Exception) {
            Log.e("CustomLiveData", "observe: HOOK 发生了异常:" + e.message)
            e.printStackTrace()
        }
    }
}