package com.example.internetpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.internetpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var myViewModel: ViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myViewModel = ViewModelProvider(this)[ViewModel::class.java]
        myViewModel.response.observe(this) {
            binding.textView.text = it
            val userName = binding.editTextTextPersonName.text.toString()
            binding.button.setOnClickListener {
                myViewModel.retrofitInit()
                myViewModel.refreshData(userName)
            }
        }
    }
}
