package com.gujun.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gujun.jetpack.lifecycle.utils.CustomLifecycle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(CustomLifecycle())
    }
}