package com.gujun.jetpack.viewmodellivedatadatabindingroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gujun.jetpack.BR
import com.gujun.jetpack.R
import com.gujun.jetpack.viewmodellivedatadatabindingroom.adapter.base.DataBindingAdapter
import com.gujun.jetpack.viewmodellivedatadatabindingroom.db.entity.User
import com.gujun.jetpack.viewmodellivedatadatabindingroom.repository.UserRepository

/**
 *    author : gujun
 *    date   : 2021/2/1 16:04
 *    desc   : User数据加载逻辑类
 */
class UserViewModel(var app: Application) : AndroidViewModel(app) {

    private var userRepository: UserRepository = UserRepository(app)

    private val mAdapter by lazy {
        DataBindingAdapter<User>(app, R.layout.layout_room_item, BR.user, BR.onItemClickListener)
    }

    fun getAllUserLiveData2(): LiveData<List<User>> {
        return userRepository.getAllUserLiveData2()
    }

    fun getAllUserLiveData(): MutableLiveData<List<User>> {
        return userRepository.getAllUserLiveData()
    }

    fun addMethod() {

        for (i in 0..10) {
            val user =
                User(
                    sid = i.toLong(),
                    name = "我是$i",
                    age = 10
                )
            userRepository.addUser(user)
        }

    }

    fun deleteMethod() {
        userRepository.deleteUserById(5)
    }

    fun updateMethod() {
        userRepository.updateUserById(2, "我是2New")
    }

    fun queryMethod() {
        userRepository.queryAllUser()
    }

    fun getAdapter(): DataBindingAdapter<User> {
        return mAdapter
    }

}