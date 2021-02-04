package com.gujun.jetpack.navigation.bottomnav

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.gujun.jetpack.R
import kotlinx.android.synthetic.main.activity_navigation_bottom.*

/**
 *    author : gujun
 *    date   : 2021/2/4 16:12
 *    desc   : 使用底部导航的Host页面
 */
class BottomNavigationTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_bottom)

        //Navigation导航图控制器
        val navController = Navigation.findNavController(this, R.id.bottomFragment)
        //配置AppBar关联bottomNavigationView
        val config = AppBarConfiguration.Builder(bottomNavigationView.menu).build()
        //导航图关联AppBar，跟随底部导航切换而改变title(是在导航图中配置的android:label)
        NavigationUI.setupActionBarWithNavController(this, navController, config)
        //直接将导航图控制器和bottomNavigationView关联，此处就不能改变AppBar的title
//        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        //bottomNavigationView关联导航图控制器
        bottomNavigationView.setupWithNavController(navController)

        //设置bottom切换监听
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.oneFragment2 -> Log.e("BottomNavigation", "onSelectOne")
                R.id.twoFragment2 -> Log.e("BottomNavigation", "onSelectTwo")
            }
        }

    }
}