package com.gujun.jetpack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gujun.jetpack.lifecycle.CustomLifecycle
import com.gujun.jetpack.livedata.DataManager
import com.gujun.jetpack.livedata.LiveDataTestActivity
import com.gujun.jetpack.navigation.NavigationHostActivity
import com.gujun.jetpack.navigation.bottomnav.BottomNavigationTestActivity
import com.gujun.jetpack.paging.PagingTestActivity
import com.gujun.jetpack.paging3.Paging3TestActivity
import com.gujun.jetpack.room.RoomTestActivity
import com.gujun.jetpack.roomviewmodellivedatacoroutine.RoomTestCoroutineActivity
import com.gujun.jetpack.viewmodel.DataAndroidViewModel
import com.gujun.jetpack.viewmodel.DataViewModel
import com.gujun.jetpack.viewmodellivedata.DataViewModelLiveData
import com.gujun.jetpack.viewmodellivedatadatabinding.DataBindingActivity
import com.gujun.jetpack.viewmodellivedatadatabindingroom.RoomLiveDataTestActivity
import com.gujun.jetpack.workmanager.WorkManagerTestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleTest()

        liveDataTest()

        viewModelTest()

        viewModelLiveDataTest()

        viewModelLiveDataDataBindingTest()

        roomTest()

        roomCoroutineTest()

        roomLiveDataTest()

        navigationTest()

        navigationBottomTest()

        pagingTest()

        paging3Test()

        workManagerTest()
    }

    private fun lifecycleTest() {
        //Lifecycle注册生命周期监听
        lifecycle.addObserver(CustomLifecycle())
    }

    private fun liveDataTest() {
        //LiveData 观察数据变化
        DataManager.getInstance().getMutableLiveData().observe(this,
            Observer<String> { content.text = it })

        change.setOnClickListener {
            //LiveData 触发数据变化
            DataManager.getInstance().getMutableLiveData().value = "我是LiveData变化后的数据"
            //在子线程使用此方法，可以通知到主线程更新
            //DataManager.getInstance().getMutableLiveData().postValue("我是子线程LiveData变化后的数据")
            //LiveData 在触发数据变化后跳转到下一个页面，看看下一个页面是否还能监听到
            startActivity(Intent(this, LiveDataTestActivity::class.java))
        }
    }

    private fun viewModelTest() {
        //ViewModel的使用，防止数据丢失，同意管理数据,ViewModelProvider.NewInstanceFactory()只适合extends ViewModel
        val dataViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DataViewModel::class.java)

        //即使页面旋转重新执行生命周期dataViewModel.number数据还是之前的
        viewModelContent.text = "我是ViewModel变化后的数据: ${dataViewModel.number}"

        //ViewModel的使用，防止数据丢失，同意管理数据,ViewModelProvider.AndroidViewModelFactory()只适合extends AndroidViewModel
        val dataViewModelAndroid = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        ).get(DataAndroidViewModel::class.java)

        //即使页面旋转重新执行生命周期dataViewModel.number数据还是之前的
        viewModelAndroidContent.text = "我是ViewModel变化后的数据: ${dataViewModelAndroid.number}"

        changeViewModel.setOnClickListener {
            dataViewModel.number = 200
            //弊端：就是每次ViewModel改变数据后还得设置文本，所以进行优化使用LiveData,参考：viewmodellivedata
            viewModelContent.text = "我是ViewModel变化后的数据: ${dataViewModel.number}"


            dataViewModelAndroid.number = 300
            //弊端：就是每次ViewModel改变数据后还得设置文本，所以进行优化使用LiveData,参考：viewmodellivedata
            viewModelAndroidContent.text = "我是ViewModel变化后的数据: ${dataViewModelAndroid.number}"
        }

    }

    private fun viewModelLiveDataTest() {
        //获取ViewModel
        val dataViewModelLiveData =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                DataViewModelLiveData::class.java
            )

        //添加LiveData监听
        dataViewModelLiveData.getLiveData().observe(this, Observer<Int> {
            //监听数据统一修改视图
            viewModelLiveDataContent.text =
                "我是ViewModel变化后的数据: ${dataViewModelLiveData.getNumber()}"
        })

        changeViewModelLiveData.setOnClickListener {
            //修改数据
            dataViewModelLiveData.setNumber(300)
        }

    }

    private fun viewModelLiveDataDataBindingTest() {
        toDataBindingActivity.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    DataBindingActivity::class.java
                )
            )
        }
    }

    private fun roomTest() {
        toRoomPage.setOnClickListener {
            startActivity(Intent(this, RoomTestActivity::class.java))
        }
    }

    private fun roomCoroutineTest() {
        toRoomCoroutinePage.setOnClickListener {
            startActivity(Intent(this, RoomTestCoroutineActivity::class.java))
        }
    }


    private fun roomLiveDataTest() {
        toRoomLiveDataPage.setOnClickListener {
            startActivity(Intent(this, RoomLiveDataTestActivity::class.java))
        }
    }

    private fun navigationTest() {
        toNavigationPage.setOnClickListener {
            startActivity(Intent(this, NavigationHostActivity::class.java))
        }
    }

    private fun navigationBottomTest() {
        toNavigationBottomPage.setOnClickListener {
            startActivity(Intent(this, BottomNavigationTestActivity::class.java))
        }
    }

    private fun pagingTest() {
        toPagingTestPage.setOnClickListener {
            startActivity(Intent(this, PagingTestActivity::class.java))
        }
    }

    private fun paging3Test() {
        toPagingTestPage3.setOnClickListener {
            startActivity(Intent(this, Paging3TestActivity::class.java))
        }
    }

    private fun workManagerTest() {
        toWorkManager.setOnClickListener {
            startActivity(Intent(this, WorkManagerTestActivity::class.java))
        }
    }

}