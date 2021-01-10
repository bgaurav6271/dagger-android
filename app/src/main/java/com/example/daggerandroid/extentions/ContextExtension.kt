package com.example.daggerandroid.extentions

import android.content.Context
import android.widget.Toast

fun Context.showToast(message : String, length : Int){
    Toast.makeText(applicationContext, message, length).show()
}