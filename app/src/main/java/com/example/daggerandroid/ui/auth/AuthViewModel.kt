package com.example.daggerandroid.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerandroid.models.UserItem
import com.example.daggerandroid.network.AuthApi
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {
    companion object{
        private const val TAG = "AuthViewModel"
    }

    private val authUser = MediatorLiveData<AuthResource<UserItem>>()

    init {
        Log.d(TAG, ": ViewModel is working")

    }

    fun observeUser() : LiveData<AuthResource<UserItem>>{
        return authUser
    }

    fun authenticateUserWithId(userId: String){
        authUser.value = AuthResource.Loading(null)
        val source = LiveDataReactiveStreams.fromPublisher(authApi
                .getUser(userId)
                .onErrorReturn(Function {
                    return@Function UserItem(id = -1)
                })
                .map(Function { user ->
                    if(user.id == -1) return@Function AuthResource.Error("Authentication Error",
                        null) else return@Function AuthResource.Authenticated(user)
                })
                .subscribeOn(Schedulers.io())
        )
        authUser.addSource(source){ userAuthResource ->
            authUser.value = userAuthResource as AuthResource<UserItem>?
            authUser.removeSource(source)
        }
    }
}