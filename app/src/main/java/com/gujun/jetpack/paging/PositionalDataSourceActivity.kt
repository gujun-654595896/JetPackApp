package com.gujun.jetpack.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gujun.jetpack.databinding.ActivityPagingPositionalBinding
import com.gujun.jetpack.paging.viewmodel.StudentPositionalViewModel

/**
 *    author : gujun
 *    date   : 2021/2/5 13:42
 *    desc   : 根据数据的起始位置、每次获取数据条数来进行分页加载
 */
class PositionalDataSourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPagingPositionalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingPositionalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                StudentPositionalViewModel::class.java
            )
        viewModel.getDataList().observe(this, Observer {
            viewModel.getAdapter().submitList(it)
        })
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

}