package com.gujun.jetpack.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 *    author : gujun
 *    date   : 2021/2/10 10:19
 *    desc   : Worker的实现类
 */
class RequestWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {

    override fun doWork(): Result {
        //此方法再子线程执行
        val result = requestData()

        //请求成功
        Data.Builder().putString("data", result).build()
        return Result.success()
    }

    private fun requestData(): String {
        Thread.sleep(5000)
        return "请求成功"
    }
}