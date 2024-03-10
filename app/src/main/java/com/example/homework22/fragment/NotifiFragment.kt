package com.example.homework22.fragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import com.example.homework22.MainActivity
import com.example.homework22.R
import com.example.homework22.databinding.FragmentNotifiBinding

class NotifiFragment : Fragment() {
    private var _binding: FragmentNotifiBinding? = null
    private val binding get() = _binding!!

    private val channelId= "notification_channel"
    private lateinit var notificationManager: NotificationManager

    private lateinit var notificationBuilder: NotificationCompat.Builder


    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotifiBinding.inflate(inflater, container, false)

        requestPermission.launch("POST_NOTIFICATION")
        notificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel()
        initNotificationBuilder()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickButtonSendMessage()
    }

    private fun createNotificationChannel(){
        val channel = NotificationChannel(
            channelId,
            "my channel",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
    }

    private fun initNotificationBuilder(){
        val intent = Intent(requireContext(), MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            requireContext(),
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        notificationBuilder = NotificationCompat.Builder(requireContext(), channelId)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("My program")
            .setContentText("Hello world")
            .setAutoCancel(true)
            .setSound(defaultSound)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
    }

    private fun clickButtonSendMessage(){
        binding.buttonSendMessage.setOnClickListener{
            notificationManager.notify(0, notificationBuilder.build())
        }
    }
}