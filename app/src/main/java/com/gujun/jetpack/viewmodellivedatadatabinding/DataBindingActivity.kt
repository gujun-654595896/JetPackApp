package com.gujun.jetpack.viewmodellivedatadatabinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gujun.jetpack.R
import com.gujun.jetpack.databinding.ActivityDatabindingBinding
import com.gujun.jetpack.viewmodellivedatadatabinding.viewmodel.DataBindingViewModel

class DataBindingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatabindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding)
        //以下方式也可以
//        binding = ActivityDatabindingBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DataBindingViewModel::class.java)

        //databinding
        binding?.viewModel = viewModel
        binding?.str = "添加文字"
        //此处setLifecycleOwner是DataBinding观察LiveData的关键，否则得自己实现LiveData观察者再更新DataBinding
        binding?.lifecycleOwner = this
    }
}