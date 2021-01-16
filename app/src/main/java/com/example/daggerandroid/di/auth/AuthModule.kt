package com.example.daggerandroid.di.auth

import com.example.daggerandroid.network.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    @AuthScope
    @Provides
    fun provideAuthApi(retrofit: Retrofit) : AuthApi{
        return retrofit.create(AuthApi::class.java)
    }
}