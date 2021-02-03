package com.gujun.jetpack.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.gujun.jetpack.R
import kotlinx.android.synthetic.main.fragment_navigation_host.*

/**
 *    author : gujun
 *    date   : 2021/2/2 16:18
 *    desc   :
 */
class HostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation_host, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toFragmentOne.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("key", "我是HostFragment带过来的数据")
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_hostFragment_to_oneFragment, bundle)
        }

        toFragmentTwo.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_hostFragment_to_twoFragment)
        }

        toActivityOne.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_hostFragment_to_oneActivity)
        }

    }
}