package com.gujun.jetpack.viewmodellivedatadatabindingroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gujun.jetpack.databinding.ActivityRoomLivedataBinding
import com.gujun.jetpack.viewmodellivedatadatabindingroom.db.entity.User
import com.gujun.jetpack.viewmodellivedatadatabindingroom.viewmodel.UserViewModel

/**
 *    author : gujun
 *    date   : 2021/1/27 10:45
 *    desc   : room操作数据库
 */
class RoomLiveDataTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomLivedataBinding

    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
            UserViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomLivedataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //1、此种方式是自己实现的LiveData，有个弊端：就是只有主动liveData.setValue/postValue时才触发刷新
//        viewModel.getAllUserLiveData().observe(this, Observer<List<User>> { userList ->
//            val stringBuilder = StringBuilder()
//            userList.forEach { stringBuilder.append(it.name).append("\n") }
//            content.text = stringBuilder
//        })

        //2、此种方式是直接使用Room返回的liveData,这样只要数据库有变化就会触发刷新，还有就是页面状态为显示时会触发页面更新
        viewModel.getAllUserLiveData2().observe(this, Observer<List<User>> { userList ->
            viewModel.getAdapter().updateItems(userList)
        })

        binding.viewModel = viewModel
    }

}