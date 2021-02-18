package com.gujun.jetpack.workmanager

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import com.gujun.jetpack.R
import com.gujun.jetpack.workmanager.ProgressWorker.Companion.Progress
import kotlinx.android.synthetic.main.activity_work_manager_test.*
import java.util.concurrent.TimeUnit


/**
 *    author : gujun
 *    date   : 2021/2/10 10:27
 *    desc   :
 */
class WorkManagerTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager_test)

        request.setOnClickListener {
            //设置任务触发条件。例如，我们可以设置在设备处于充电，网络已连接，且电池电量充足的状态下，才出发我们设置的任务。
            val constraints: Constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build()
            //创建WorkRequest
            val workRequest =
                OneTimeWorkRequestBuilder<RequestWorker>()
                    .setConstraints(constraints)
                    .setInputData(workDataOf("key" to "12", "key2" to "13")).build()
            //执行异步任务
            WorkManager.getInstance(this@WorkManagerTestActivity).enqueue(workRequest)
            //添加任务执行结果监听
            WorkManager.getInstance(this@WorkManagerTestActivity)
                .getWorkInfoByIdLiveData(workRequest.id).observe(this,
                    Observer {
                        when (it.state) {
                            WorkInfo.State.SUCCEEDED -> Log.e(
                                "WorkManagerTestActivity",
                                "success: ${it.outputData.getString("data")}"
                            )

                            WorkInfo.State.RUNNING -> Log.e(
                                "WorkManagerTestActivity",
                                "running:"
                            )

                            else -> Log.e(
                                "WorkManagerTestActivity",
                                "state: ${it.state}"
                            )
                        }

                    })

        }

        progressRequest.setOnClickListener {
            //设置任务触发条件。例如，我们可以设置在设备处于充电，网络已连接，且电池电量充足的状态下，才出发我们设置的任务。
            val constraints: Constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build()
            //创建WorkRequest
            val workRequest =
                OneTimeWorkRequestBuilder<ProgressWorker>()
                    .setConstraints(constraints).build()
            //执行异步任务
            WorkManager.getInstance(this@WorkManagerTestActivity).enqueue(workRequest)
            //添加任务执行结果监听
            WorkManager.getInstance(this@WorkManagerTestActivity)
                .getWorkInfoByIdLiveData(workRequest.id).observe(this,
                    Observer {

                        when (it.state) {
                            WorkInfo.State.ENQUEUED -> progressContent.text = "0"

                            WorkInfo.State.RUNNING -> progressContent.text =
                                it.progress.getInt(Progress, 0).toString()

                            WorkInfo.State.SUCCEEDED -> progressContent.text = "100"
                        }

                    })
        }

        chainRequest.setOnClickListener {
            //设置任务触发条件。例如，我们可以设置在设备处于充电，网络已连接，且电池电量充足的状态下，才出发我们设置的任务。
            val constraints: Constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build()

            val workRequest1 =
                OneTimeWorkRequestBuilder<RequestWorker>()
                    // 设置退避让政策，当Worker中return Result.retry()后会执行此政策
                    // 在本示例中，最短退避延迟时间设置为允许的最小值，即 10 秒。由于政策为 LINEAR，每次尝试重试时，
                    // 重试间隔都会增加约 10 秒。例如，第一次运行以 Result.retry() 结束并在 10 秒后重试；
                    // 然后，如果工作在后续尝试后继续返回 Result.retry()，那么接下来会在 20 秒、30 秒、40 秒后重试，以此类推。
                    // 如果退避政策设置为 EXPONENTIAL，那么重试时长序列将接近 20、40、80 秒，以此类推。
                    // 注意：退避延迟时间不精确，在两次重试之间可能会有几秒钟的差异，但绝不会低于配置中指定的初始退避延迟时间。
                    .setBackoffCriteria(
                        BackoffPolicy.LINEAR,
                        OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                        TimeUnit.MILLISECONDS
                    )
                    .setConstraints(constraints)
                    .setInputData(workDataOf("key" to "1", "key2" to "2")).build()

            val workRequest2 =
                OneTimeWorkRequestBuilder<RequestWorker>()
                    .setConstraints(constraints)
                    .setInputData(workDataOf("key" to "3", "key2" to "4")).build()

            val workRequest3 =
                OneTimeWorkRequestBuilder<RequestWorker>()
                    .setConstraints(constraints)
                    .setInputData(workDataOf("key" to "5", "key2" to "6")).build()

            WorkManager.getInstance(this@WorkManagerTestActivity)
                .beginWith(workRequest1)
                .then(workRequest2)
                .then(workRequest3)
                .enqueue()

            //添加任务执行结果监听
            WorkManager.getInstance(this@WorkManagerTestActivity)
                .getWorkInfoByIdLiveData(workRequest1.id).observe(this,
                    Observer {
                        when (it.state) {
                            WorkInfo.State.SUCCEEDED -> Log.e(
                                "workRequest1",
                                "success: ${it.outputData.getString("data")}"
                            )

                            WorkInfo.State.RUNNING -> Log.e(
                                "workRequest1",
                                "running:"
                            )

                            else -> Log.e(
                                "workRequest1",
                                "state: ${it.state}"
                            )
                        }

                    })

            //添加任务执行结果监听
            WorkManager.getInstance(this@WorkManagerTestActivity)
                .getWorkInfoByIdLiveData(workRequest2.id).observe(this,
                    Observer {
                        when (it.state) {
                            WorkInfo.State.SUCCEEDED -> Log.e(
                                "workRequest2",
                                "success: ${it.outputData.getString("data")}"
                            )

                            WorkInfo.State.RUNNING -> Log.e(
                                "workRequest2",
                                "running:"
                            )

                            else -> Log.e(
                                "workRequest2",
                                "state: ${it.state}"
                            )
                        }

                    })

            //添加任务执行结果监听
            WorkManager.getInstance(this@WorkManagerTestActivity)
                .getWorkInfoByIdLiveData(workRequest3.id).observe(this,
                    Observer {
                        when (it.state) {
                            WorkInfo.State.SUCCEEDED -> Log.e(
                                "workRequest3",
                                "success: ${it.outputData.getString("data")}"
                            )

                            WorkInfo.State.RUNNING -> Log.e(
                                "workRequest3",
                                "running:"
                            )

                            else -> Log.e(
                                "workRequest3",
                                "state: ${it.state}"
                            )
                        }

                    })
        }
    }
}