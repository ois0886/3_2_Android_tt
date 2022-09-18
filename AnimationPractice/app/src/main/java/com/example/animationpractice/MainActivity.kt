package com.example.animationpractice

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ValueAnimator.ofFloat(0F, 100F).apply {
            addUpdateListener { println(it.animatedValue) }
            start()
        }
    }
}