package com.example.animationpractice3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    val scene1Livedata: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val scene2Livedata: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        scene1Livedata.value = "Name"
        scene2Livedata.value = "Name"
    }

    fun load() {

    }
}