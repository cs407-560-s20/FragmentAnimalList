package com.example.fragmentanimallist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    val position = MutableLiveData<Int>()
}