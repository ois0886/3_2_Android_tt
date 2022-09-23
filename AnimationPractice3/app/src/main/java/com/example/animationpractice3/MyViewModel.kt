package com.example.animationpractice3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    var scene1Text1 = MutableLiveData<String>()
    var scene1Text2 = MutableLiveData<String>()
}