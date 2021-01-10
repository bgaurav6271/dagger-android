package com.example.daggerandroid.network

import com.example.daggerandroid.models.UserItem
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("users/{id}")
    fun getUser(@Path("id") id : String) : Flowable<UserItem>
}