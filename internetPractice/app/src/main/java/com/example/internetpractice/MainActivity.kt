package com.example.internetpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.internetpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var myViewModel: ViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        myViewModel.response.observe(this) {
            findViewById<TextView>(R.id.textView).text = it
        }
    }
}
