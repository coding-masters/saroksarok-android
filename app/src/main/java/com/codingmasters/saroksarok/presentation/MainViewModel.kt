package com.codingmasters.saroksarok.presentation

import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    private var name:String = "tnals"

    fun saveName(user:String){
        name=user
    }

    fun getName(): String = name
}