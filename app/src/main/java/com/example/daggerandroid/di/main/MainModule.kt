package com.example.daggerandroid.di.main

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggerandroid.R
import com.example.daggerandroid.network.MainApi
import com.example.daggerandroid.ui.main.MainActivity
import com.example.daggerandroid.ui.main.posts.PostsRecyclerAdapter
import com.example.daggerandroid.utils.VerticalSpacingItemDecoration
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    companion object{
        @Provides
        fun provideMainApi(retrofit: Retrofit): MainApi = retrofit.create(MainApi::class.java)

        @Provides
        fun provideAdapter() : PostsRecyclerAdapter = PostsRecyclerAdapter()

        @Provides
        fun provideLayoutManager(activity: MainActivity) : LinearLayoutManager = LinearLayoutManager(activity)

        @Provides
        fun provideItemDecorator() : VerticalSpacingItemDecoration = VerticalSpacingItemDecoration(15)
    }

}