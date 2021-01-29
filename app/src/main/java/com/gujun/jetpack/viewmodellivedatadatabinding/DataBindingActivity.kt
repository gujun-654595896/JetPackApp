package com.gujun.jetpack.viewmodellivedatadatabinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gujun.jetpack.R
import com.gujun.jetpack.databinding.ActivityDatabindingBinding
import com.gujun.jetpack.viewmodellivedatadatabinding.viewmodel.DataBindingViewModel

class DataBindingActivity : AppCompatActivity() {

    private var binding: ActivityDatabindingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DataBindingViewModel::class.java)

        //databinding
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this
    }
}