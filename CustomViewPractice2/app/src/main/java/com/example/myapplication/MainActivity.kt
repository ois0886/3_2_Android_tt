package com.example.myapplication

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

var checkedNum = 0
class MainActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val radioGroup: RadioGroup = findViewById(R.id.radioGp)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_Circle -> checkedNum = 1
                R.id.radio_Rectangle -> checkedNum = 2
            }
        }
    }
}