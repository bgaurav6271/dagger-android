package com.example.daggerandroid.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.daggerandroid.SessionManager
import com.example.daggerandroid.models.UserItem
import com.example.daggerandroid.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val sessionManager: SessionManager) : ViewModel() {

    companion object{
        private const val TAG = "ProfileViewModel"
    }

    init {
        Log.d(TAG, ": ViewModel is ready")
    }

    fun getAuthenticatedUser() : LiveData<AuthResource<UserItem>>{
        return sessionManager.getAuthUser()
    }
}