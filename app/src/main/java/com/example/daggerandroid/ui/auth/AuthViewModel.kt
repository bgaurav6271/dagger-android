package com.example.daggerandroid.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerandroid.SessionManager
import com.example.daggerandroid.models.UserItem
import com.example.daggerandroid.network.AuthApi
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi,
                                        private val sessionManager: SessionManager) : ViewModel() {
    companion object{
        private const val TAG = "AuthViewModel"
    }

    init {
        Log.d(TAG, ": ViewModel is working")

    }

    fun observeAuthState() : LiveData<AuthResource<UserItem>>{
        return sessionManager.getAuthUser()
    }

    fun authenticateUserWithId(userId: String){
        Log.d(TAG, "authenticateUserWithId: attempting to Login")
        sessionManager.authenticateWithId(queryUserId(userId))
    }

    private fun queryUserId(userId : String) : LiveData<AuthResource<UserItem>>{
        return LiveDataReactiveStreams.fromPublisher(authApi
                .getUser(userId)
                .onErrorReturn(Function {
                    return@Function UserItem(id = -1)
                })
                .map(Function { user ->
                    if(user.id == -1) return@Function AuthResource.Error(
                            "Authentication Error", null) as AuthResource<UserItem>
                    else return@Function AuthResource.Authenticated(user) as AuthResource<UserItem>
                })
                .subscribeOn(Schedulers.io()))
    }
}