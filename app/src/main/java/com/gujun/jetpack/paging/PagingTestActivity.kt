package com.gujun.jetpack.paging

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gujun.jetpack.R
import kotlinx.android.synthetic.main.activity_paging_test.*

/**
 *    author : gujun
 *    date   : 2021/2/5 13:34
 *    desc   :
 */
class PagingTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging_test)
        toPositionalDataSource.setOnClickListener {
            startActivity(Intent(this, PositionalDataSourceActivity::class.java))
        }

        toPageKeyedDataSource.setOnClickListener {
            startActivity(Intent(this, PageKeyedDataSourceActivity::class.java))
        }

        toItemKeyedDataSource.setOnClickListener {
            startActivity(Intent(this, ItemKeyedDataSourceActivity::class.java))
        }
    }

}