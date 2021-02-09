package com.gujun.jetpack.paging3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.gujun.jetpack.databinding.ActivityPaging3DatasourceBinding
import com.gujun.jetpack.paging3.viewmodel.Student3ViewModel

/**
 *    author : gujun
 *    date   : 2021/2/5 13:42
 *    desc   : 根据数据的起始位置、每次获取数据条数来进行分页加载
 */
class Paging3DataSourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaging3DatasourceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaging3DatasourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                Student3ViewModel::class.java
            )
        viewModel.getData().observe(this, Observer {
            lifecycleScope.launchWhenCreated {
                //此处要使用协程环境，submitData 是suspend 方法
                viewModel.getAdapter().submitData(it)
            }
        })
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

}