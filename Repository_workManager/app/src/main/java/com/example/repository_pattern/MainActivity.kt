package com.example.repository_pattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.example.repository_pattern.databinding.ActivityMainBinding
import java.lang.StringBuilder
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var myViewModel: MyViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userName = binding.editTextTextPersonName.text.toString()
        findViewById<Button>(R.id.startWorker).setOnClickListener { startWorker(userName) }
        findViewById<Button>(R.id.stopWorker).setOnClickListener { stopWorker() }

        myViewModel =
            ViewModelProvider(this, MyViewModel.Factory(this))[MyViewModel::class.java]

        myViewModel.repos.observe(this) { repos ->
            val response = StringBuilder().apply {
                repos.forEach {
                    append(it.name)
                    append(" - ")
                    append(it.owner)
                    append("\n")
                }
            }.toString()
            findViewById<TextView>(R.id.textResponse).text = response
        }

        WorkManager.getInstance(this).getWorkInfosForUniqueWorkLiveData(MyWorker.name)
            .observe(this) { workInfo ->
                if (workInfo.isNotEmpty()) {
                    when (workInfo[0].state) {
                        WorkInfo.State.ENQUEUED -> println("Worker enqueued!")
                        WorkInfo.State.RUNNING -> println("Worker running!")
                        WorkInfo.State.SUCCEEDED -> println("Worker succeeded!")  // only for one time worker
                        WorkInfo.State.CANCELLED -> println("Worker cancelled!")
                        else -> println(workInfo[0].state)
                    }
                }
            }
    }

    private fun startWorker(userName: String) {

        val constraints = Constraints.Builder().apply {
            setRequiredNetworkType(NetworkType.UNMETERED) // un-metered network such as WiFi
            setRequiresBatteryNotLow(true)
        }.build()

        val repeatingRequest = PeriodicWorkRequestBuilder<MyWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(workDataOf("username" to userName))
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            MyWorker.name,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }

    private fun stopWorker() {
        WorkManager.getInstance(this).cancelUniqueWork(MyWorker.name)
    }
}