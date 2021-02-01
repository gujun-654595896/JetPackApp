package com.gujun.jetpack.viewmodellivedatadatabindingroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gujun.jetpack.viewmodellivedatadatabindingroom.database.RoomDatabaseHelper
import com.gujun.jetpack.viewmodellivedatadatabindingroom.entity.User
import kotlin.concurrent.thread

/**
 *    author : gujun
 *    date   : 2021/2/1 16:04
 *    desc   : User数据加载逻辑类
 */
class UserViewModel(var app: Application) : AndroidViewModel(app) {

    //初始化 liveData
    private val liveData: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>()
    }

    //此种方式不推荐
    //自己实现LiveData，局限性比较大
    fun getAllUserLiveData(): MutableLiveData<List<User>> {
        return liveData
    }

    fun addUser(user: User) {
        thread {
            RoomDatabaseHelper.getInstance(app).getUserDao().addUser(user)
        }
    }

    fun deleteUserById(sid: Long) {
        thread {
            RoomDatabaseHelper.getInstance(app).getUserDao().deleteUserById(sid)
        }
    }

    fun updateUserById(sid: Long, name: String) {
        thread {
            RoomDatabaseHelper.getInstance(app).getUserDao().updateUserById(name, sid)
        }
    }

    fun queryAllUser() {
        thread {
            //子线程设置数据要用postValue
            liveData.postValue(RoomDatabaseHelper.getInstance(app).getUserDao().getAllUser())
        }
    }

    // 推荐用此种方式，而不是使用自己创建的liveData
    // 只要数据库有变化就会触发LiveData刷新
    // 还有个优点是此处不用使用线程
    fun getAllUserLiveData2(): LiveData<List<User>> {
        return RoomDatabaseHelper.getInstance(app).getUserDao().getAllUserLiveData()
    }

}