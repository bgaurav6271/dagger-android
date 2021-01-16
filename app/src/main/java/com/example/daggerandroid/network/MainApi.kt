package com.example.daggerandroid.network

import com.example.daggerandroid.models.Post
import com.example.daggerandroid.models.UserItem
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {
    @GET("posts")
    fun getPostsFromUser(@Query("userId") id : String) : Flowable<List<Post>>
}