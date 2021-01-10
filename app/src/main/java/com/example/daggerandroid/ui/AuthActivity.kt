package com.example.daggerandroid.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.example.daggerandroid.R
import com.example.daggerandroid.extentions.showToast
import com.example.daggerandroid.invisisble
import com.example.daggerandroid.ui.auth.AuthResource
import com.example.daggerandroid.ui.auth.AuthViewModel
import com.example.daggerandroid.viewmodels.ViewModelProviderFactory
import com.example.daggerandroid.visible
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    companion object{
        private const val TAG = "AuthActivity"
    }

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var requestManager : RequestManager

    @Inject
    lateinit var logo : Drawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        login_button.setOnClickListener(this)

        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)

        setLogo()
        subscribeObservers()
    }

    private fun subscribeObservers() {
       viewModel.observeUser().observe(this){ authResource ->
           when(authResource){
               is AuthResource.Authenticated -> {
                   showProgressBar(false)
                   Log.d(TAG, "subscribeObservers: Login Success ${authResource.data?.email}")
               }
               is AuthResource.Error -> {
                   showProgressBar(false)
                   showToast("${authResource.message}! Is ID valid", Toast.LENGTH_SHORT)
               }
               is AuthResource.Loading -> showProgressBar(true)
               is AuthResource.NotAuthenticated -> showProgressBar(false)
           }
       }
    }

    private fun showProgressBar(isVisible: Boolean){
        if(isVisible) progress_bar.visible() else progress_bar.invisisble()
    }

    private fun setLogo() {
        requestManager
                .load(logo)
                .into(login_logo)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.login_button -> attemptLogin()
        }
    }

    private fun attemptLogin() {
        if(user_id_input.text.isNullOrEmpty()){
            return
        }
        viewModel.authenticateUserWithId(user_id_input.text.toString())
    }
}