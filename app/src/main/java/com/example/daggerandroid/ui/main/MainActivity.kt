package com.example.daggerandroid.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import com.example.daggerandroid.BaseActivity
import com.example.daggerandroid.R
import com.example.daggerandroid.extentions.showToast

class MainActivity : BaseActivity() {
    companion object{
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        showToast("Entered Main Activity", Toast.LENGTH_SHORT)
    }
}