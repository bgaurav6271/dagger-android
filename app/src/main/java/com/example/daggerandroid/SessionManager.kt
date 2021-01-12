package com.example.daggerandroid

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.daggerandroid.models.UserItem
import com.example.daggerandroid.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(){
    companion object{
        private const val TAG = "SessionManager"
    }

    /**
     * Mediator Live Data so that we can observe the authenticated user from any class
     * that we inject the SessionManager into
     */
    private val cachedUser : MediatorLiveData<AuthResource<UserItem>> = MediatorLiveData()

    fun authenticateWithId(source: LiveData<AuthResource<UserItem>>) {
        cachedUser.apply {
            cachedUser.value = AuthResource.Loading()
            cachedUser.addSource(source){
                cachedUser.value = it
                cachedUser.removeSource(source)
            }
        }
    }

    fun logOut(){
        Log.d(TAG, "logOut: logging out")
        cachedUser.value = AuthResource.NotAuthenticated()
    }

    fun getAuthUser() : LiveData<AuthResource<UserItem>>{
        return cachedUser
    }
}