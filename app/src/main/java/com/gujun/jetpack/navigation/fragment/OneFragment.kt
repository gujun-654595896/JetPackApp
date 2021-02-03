package com.gujun.jetpack.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.gujun.jetpack.R
import com.gujun.jetpack.viewmodel.DataViewModel
import kotlinx.android.synthetic.main.fragment_navigation_one.*

/**
 *    author : gujun
 *    date   : 2021/2/2 16:18
 *    desc   :
 */
class OneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toActivityOne.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_oneFragment_to_oneActivity)
        }

        //获取Activity中的ViewModel，共享数据
        activityViewModel.text =
            ViewModelProvider(requireActivity()).get(DataViewModel::class.java).number.toString()

        //获取Bundle携带的参数
        bundleContent.text = arguments?.getString("key")

    }
}