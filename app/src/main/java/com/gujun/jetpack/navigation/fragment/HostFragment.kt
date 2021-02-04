package com.gujun.jetpack.navigation.fragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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
            //方式一：通过Bundle
//            val bundle = Bundle()
//            bundle.putString("key", "我是HostFragment带过来的数据")
//            NavHostFragment.findNavController(this)
//                .navigate(R.id.action_hostFragment_to_oneFragment, bundle)

            //方式二：通过SafeArgs
            NavHostFragment.findNavController(this)
                .navigate(
                    HostFragmentDirections.actionHostFragmentToOneFragment()
                        .setKey("我是通过插件SafeArgs设置的数据")
                )

        }

        toFragmentTwo.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_hostFragment_to_twoFragment)
        }

        toActivityOne.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_hostFragment_to_oneActivity)
        }

        toFragmentThree.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(Uri.parse("three://com.gujun.test/123="))
        }

        toFragmentThreePaddingIntent.setOnClickListener {
            sendNotification()
        }

    }

    private fun sendNotification() {
        val channelId = "channel_test"
        val notificationId = 100

        if (activity == null) {
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                NotificationChannel(channelId, "ChannelName", importance)
            channel.description = "description"
            val notificationManager =
                requireActivity().getSystemService(
                    NotificationManager::class.java
                )
            notificationManager.createNotificationChannel(channel)
        }
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(requireActivity(), channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("DeepLink")
                .setContentText("Hello World")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .setAutoCancel(true)
        val notificationManager =
            NotificationManagerCompat.from(requireActivity())
        notificationManager.notify(notificationId, builder.build())
    }


    private fun getPendingIntent(): PendingIntent? {
        if (activity != null) {
            val bundle = Bundle()
            bundle.putString("path", "path From Notification")
            return NavHostFragment.findNavController(this)
                .createDeepLink()
                .setGraph(R.navigation.nav_test)
                .setDestination(R.id.threeFragment)
                .setArguments(bundle)
                .createPendingIntent()
        }
        return null
    }
}