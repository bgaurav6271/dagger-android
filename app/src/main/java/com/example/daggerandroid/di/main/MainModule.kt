package com.example.daggerandroid.di.main

import com.example.daggerandroid.network.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {


    companion object{
        @Provides
        fun provideMainApi(retrofit: Retrofit) = retrofit.create(MainApi::class.java)
    }

}