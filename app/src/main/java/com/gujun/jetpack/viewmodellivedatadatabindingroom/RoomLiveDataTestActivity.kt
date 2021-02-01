package com.gujun.jetpack.viewmodellivedatadatabindingroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gujun.jetpack.R
import com.gujun.jetpack.viewmodellivedatadatabindingroom.entity.User
import com.gujun.jetpack.viewmodellivedatadatabindingroom.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_room.*

/**
 *    author : gujun
 *    date   : 2021/1/27 10:45
 *    desc   : room操作数据库
 */
class RoomLiveDataTestActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
            UserViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        addBtn.setOnClickListener { addMethod() }
        deleteBtn.setOnClickListener { deleteMethod() }
        updateBtn.setOnClickListener { updateMethod() }
        queryBtn.setOnClickListener { queryMethod() }

        //1、此种方式是自己实现的LiveData，有个弊端：就是只有主动liveData.setValue/postValue时才触发刷新
//        viewModel.getAllUserLiveData().observe(this, Observer<List<User>> { userList ->
//            val stringBuilder = StringBuilder()
//            userList.forEach { stringBuilder.append(it.name).append("\n") }
//            content.text = stringBuilder
//        })

        //2、此种方式是直接使用Room返回的liveData,这样只要数据库有变化就会触发刷新，还有就是页面状态为显示时会触发页面更新
        viewModel.getAllUserLiveData2().observe(this, Observer<List<User>> { userList ->
            val stringBuilder = StringBuilder()
            userList.forEach { stringBuilder.append(it.name).append("\n") }
            content.text = stringBuilder
        })

    }

    private fun addMethod() {

        for (i in 0..10) {
            val user =
                User(
                    sid = i.toLong(),
                    name = "我是$i",
                    age = 10
                )
            viewModel.addUser(user)
        }

    }

    private fun deleteMethod() {
        viewModel.deleteUserById(5)
    }

    private fun updateMethod() {
        viewModel.updateUserById(2, "我是2New")
    }

    private fun queryMethod() {
        viewModel.queryAllUser()
    }
}