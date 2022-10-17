package com.example.repository_pattern

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class MyWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        val userName = inputData.getString("username") ?: ""
        val repository = MyRepository(applicationContext)
        try {
            repository.refreshData(userName)
        } catch (e: Exception) {
            return Result.retry()
        }
        return Result.success()
    }

    companion object {
        const val name = "com.example.repository_pattern.MyWorker"
    }
}
