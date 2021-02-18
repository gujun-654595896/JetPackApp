package com.gujun.jetpack.workmanager

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import com.gujun.jetpack.R
import kotlinx.android.synthetic.main.activity_work_manager_test.*


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
    }
}