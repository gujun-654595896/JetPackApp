package com.gujun.jetpack.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gujun.jetpack.databinding.ActivityPagingItemkeyedBinding
import com.gujun.jetpack.paging.viewmodel.StudentItemKeyedViewModel

/**
 *    author : gujun
 *    date   : 2021/2/5 13:46
 *    desc   : 根据上一页最后一条数据的关键key值、关键key值后面取多少条来进行分页加载
 */
class ItemKeyedDataSourceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPagingItemkeyedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingItemkeyedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                StudentItemKeyedViewModel::class.java
            )
        viewModel.getDataList().observe(this, Observer {
            viewModel.getAdapter().submitList(it)
        })
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}