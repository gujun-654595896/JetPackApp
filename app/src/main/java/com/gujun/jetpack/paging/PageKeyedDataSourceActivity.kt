package com.gujun.jetpack.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gujun.jetpack.databinding.ActivityPagingPagekeyedBinding
import com.gujun.jetpack.paging.viewmodel.StudentPageKeyedViewModel

/**
 *    author : gujun
 *    date   : 2021/2/5 13:44
 *    desc   : 根据数据的页数、一页多少条数据来进行分页加载的
 */
class PageKeyedDataSourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPagingPagekeyedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingPagekeyedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                StudentPageKeyedViewModel::class.java
            )
        viewModel.getDataList().observe(this, Observer {
            viewModel.getAdapter().submitList(it)
        })
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}