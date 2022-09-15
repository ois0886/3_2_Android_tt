package com.example.notificationpratice2

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notificationpratice2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val channelID = "default"
    private val myChannelID = "ad"

    @RequiresApi(33)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Manifest.permission.POST_NOTIFICATIONS.requestSinglePermission()

        var check = 1
        val channel = 1

        binding.buttonShowNotification.setOnClickListener {
            val message = binding.editTextTextPersonName.text.toString()
            showNotification(channel, check, message)
            check += 1
        }

        binding.buttonNotify.setOnClickListener {
            val message = binding.editTextTextPersonName.text.toString()
            showNotification(channel + 1, check, message)
        }

        createNotificationChannel()
    }

    private fun showNotification(channel: Int, check: Int, message: String) {
        if (channel == 1) {
            val builder = NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification Lab$channel")
                .setContentText("""Notification #$check""")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            NotificationManagerCompat.from(this)
                .notify(channel, builder.build())
        } else {
            val builder = NotificationCompat.Builder(this, myChannelID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification Lab$channel")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            NotificationManagerCompat.from(this)
                .notify(channel, builder.build())
        }
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            channelID, "default channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val channel2 = NotificationChannel(
            myChannelID, "ad channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = "description text of this channel."
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        notificationManager.createNotificationChannel(channel2)
    }

    private fun String.requestSinglePermission() {
        if (checkSelfPermission(this) == PackageManager.PERMISSION_GRANTED)
            return

        val requestPermLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (it == false) { // permission is not granted!
                    AlertDialog.Builder(this@MainActivity).apply {
                        setTitle("Warning")
                        setMessage("Warning")
                    }.show()
                }
            }

        if (shouldShowRequestPermissionRationale(this)) {
            // you should explain the reason why this app needs the permission.
            AlertDialog.Builder(this@MainActivity).apply {
                setTitle("Reason")
                setMessage("Reason")
                setPositiveButton("Allow") { _, _ -> requestPermLauncher.launch(this@requestSinglePermission) }
                setNegativeButton("Deny") { _, _ -> }
            }.show()
        } else {
            // should be called in onCreate()
            requestPermLauncher.launch(this)
        }
    }

}