package com.gujun.jetpack.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.gujun.jetpack.R
import kotlinx.android.synthetic.main.fragment_navigation_three.*

/**
 *    author : gujun
 *    date   : 2021/2/2 16:18
 *    desc   :
 */
class ThreeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation_three, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }
    }
}