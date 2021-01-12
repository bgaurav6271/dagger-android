package com.example.daggerandroid

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import com.example.daggerandroid.extentions.showToast
import com.example.daggerandroid.ui.AuthActivity
import com.example.daggerandroid.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(){
    companion object{
        private const val TAG = "BaseActivity"
    }

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        Log.d(TAG, "subscribeObservers: subscribing")
        sessionManager.getAuthUser().observe(this){ authResource ->
            when(authResource){
                is AuthResource.Authenticated -> {
                    Log.d(TAG, "subscribeObservers: Login Success ${authResource.data?.email}")
                }
                is AuthResource.Error -> {
                    showToast("${authResource.message}! Is ID valid", Toast.LENGTH_SHORT)
                }
                is AuthResource.Loading -> showToast("Authentication Loading", Toast.LENGTH_SHORT)
                is AuthResource.NotAuthenticated -> navLoginScreen()
            }
        }
    }

    private fun navLoginScreen(){
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}