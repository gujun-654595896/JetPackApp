package com.gujun.jetpack.viewmodellivedatadatabindingroom.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gujun.jetpack.viewmodellivedatadatabindingroom.db.database.RoomDatabaseHelper
import com.gujun.jetpack.viewmodellivedatadatabindingroom.db.entity.User
import kotlin.concurrent.thread

/**
 *    author : gujun
 *    date   : 2021/2/2 11:31
 *    desc   : 数据仓库，主要存放数据库操作和LiveData
 */
class UserRepository(val context: Context) {

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
            RoomDatabaseHelper.getInstance(context).getUserDao().addUser(user)
        }
    }

    fun deleteUserById(sid: Long) {
        thread {
            RoomDatabaseHelper.getInstance(context).getUserDao().deleteUserById(sid)
        }
    }

    fun updateUserById(sid: Long, name: String) {
        thread {
            RoomDatabaseHelper.getInstance(context).getUserDao().updateUserById(name, sid)
        }
    }

    fun queryAllUser() {
        thread {
            //子线程设置数据要用postValue
            liveData.postValue(RoomDatabaseHelper.getInstance(context).getUserDao().getAllUser())
        }
    }

    // 推荐用此种方式，而不是使用自己创建的liveData
    // 只要数据库有变化就会触发LiveData刷新
    // 还有个优点是此处不用使用线程
    fun getAllUserLiveData2(): LiveData<List<User>> {
        return RoomDatabaseHelper.getInstance(context).getUserDao().getAllUserLiveData()
    }

}