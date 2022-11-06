package com.example.firebaseex

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseex.databinding.ActivityPracBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.storage.ktx.storage

class PracActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPracBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rootRef = Firebase.storage.reference
        val ref = rootRef.child("여름의+골목+계단.jpg")
        ref.getBytes(Long.MAX_VALUE).addOnCompleteListener {
            if (it.isSuccessful) {
                val bmp = BitmapFactory.decodeByteArray(it.result, 0, it.result!!.size)
                binding.imageView3.setImageBitmap(bmp)
            }
        }
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.setDefaultsAsync(R.xml.rc_defaults)
        val settings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 1 // For test purpose only, 3600 seconds for production
        }
        remoteConfig.setConfigSettingsAsync(settings)
        remoteConfig.fetchAndActivate().addOnSuccessListener {
            remoteConfig.getString("season")
        }
    }
}