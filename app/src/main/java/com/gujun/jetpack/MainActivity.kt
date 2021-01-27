package com.gujun.jetpack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gujun.jetpack.lifecycle.CustomLifecycle
import com.gujun.jetpack.livedata.DataManager
import com.gujun.jetpack.livedata.LiveDataTestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleTest()

        liveDataTest()

    }

    private fun lifecycleTest() {
        //Lifecycle注册生命周期监听
        lifecycle.addObserver(CustomLifecycle())
    }

    private fun liveDataTest() {
        //LiveData 观察数据变化
        DataManager.getInstance().getMutableLiveData().observe(this,
            Observer<String> { content.text = it })

        change.setOnClickListener {
            //LiveData 触发数据变化
            DataManager.getInstance().getMutableLiveData().value = "我是LiveData变化后的数据"
            //LiveData 在触发数据变化后跳转到下一个页面，看看下一个页面是否还能监听到
            startActivity(Intent(this, LiveDataTestActivity::class.java))
        }
    }
}