package com.gujun.jetpack.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gujun.jetpack.R
import com.gujun.jetpack.viewmodel.DataViewModel

class NavigationHostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_host)

        //子Fragment能共享此ViewModel
        //ViewModel的使用，防止数据丢失，同意管理数据,ViewModelProvider.NewInstanceFactory()只适合extends ViewModel
        val dataViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DataViewModel::class.java)

        dataViewModel.number = 30
    }
}