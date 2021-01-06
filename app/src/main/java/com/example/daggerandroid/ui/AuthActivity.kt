package com.example.daggerandroid.ui

import android.os.Bundle
import android.widget.TextView
import com.example.daggerandroid.BaseApplication
import com.example.daggerandroid.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var someText : String

    private lateinit var tvTest : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        tvTest = findViewById(R.id.tvTest)
        if(this::someText.isInitialized){
            tvTest.text = someText
        } else{
            tvTest.text = "String is Null"
        }
    }
}