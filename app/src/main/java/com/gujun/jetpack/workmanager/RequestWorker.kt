package com.gujun.jetpack.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

/**
 *    author : gujun
 *    date   : 2021/2/10 10:19
 *    desc   : Worker的实现类
 */
class RequestWorker(val context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {

    override fun doWork(): Result {
        //此方法再子线程执行

        //传递进来的参数
        val key = inputData.getString("key")
        val key2 = inputData.getString("key2")
        Log.e("RequestWorker", "参数: key=$key , key2=$key2")

        val result = requestData()

//        //重试策略
//        if (key2 == "4") {
//            return Result.retry()
//        }
        //请求成功，data是传递出去的值
        return Result.success(workDataOf("data" to result))
    }

    private fun requestData(): String {
        Thread.sleep(5000)
        return "请求成功"
    }

}