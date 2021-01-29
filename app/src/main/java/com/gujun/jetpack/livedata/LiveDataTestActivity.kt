package com.gujun.jetpack.livedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gujun.jetpack.R
import kotlinx.android.synthetic.main.activity_live_data.*

/**
 *    author : gujun
 *    date   : 2021/1/27 10:45
 *    desc   : 测试LiveData的数据通知
 */
class LiveDataTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        //LiveData 观察数据变化
        //在进入此页面前数据就触发了变化，进入此页面后还能监听到变化后的数据
        //使用LivedData原始代码，数据是粘性的，也就是在调用observe监听前setValue了,但是当生命周期状态从不可见到可见后还是会回调observe监听
        DataManager.getInstance().getMutableLiveData().observe(this,
            Observer<String> { content.text = it })

        //一般LiveData也可使用在接口请求后通知页面更新数据
        LiveDataRequestUtil.getInstance().getMutableLiveData().observe(this,
            Observer<String> { contentRequest.text = "我是接口返回的数据:$it" })
        //请求接口数据
        LiveDataRequestUtil.getInstance().requestData()
    }
}