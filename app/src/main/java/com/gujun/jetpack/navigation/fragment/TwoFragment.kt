package com.gujun.jetpack.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.gujun.jetpack.R
import kotlinx.android.synthetic.main.fragment_navigation_two.*

/**
 *    author : gujun
 *    date   : 2021/2/2 16:18
 *    desc   :
 */
class TwoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toFragmentThree.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_twoFragment_to_threeFragment)
        }

    }
}