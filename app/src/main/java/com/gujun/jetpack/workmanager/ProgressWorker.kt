package com.gujun.jetpack.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

/**
 *    author : gujun
 *    date   : 2021/2/10 10:19
 *    desc   : Worker的实现类,观察进度的类
 */
class ProgressWorker(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {

    companion object {
        const val Progress = "Progress"
        private const val delayDuration = 500L
    }

    override suspend fun doWork(): Result {
        val firstUpdate = workDataOf(Progress to 0)
        val lastUpdate = workDataOf(Progress to 50)
        setProgress(firstUpdate)
        delay(delayDuration)
        setProgress(lastUpdate)
        delay(delayDuration)
        return Result.success()
    }
}