package com.example.daggerandroid.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.daggerandroid.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    fun provideRequestOptions() : RequestOptions = RequestOptions
            .placeholderOf(R.drawable.white_background)
            .error(R.drawable.white_background)

    @Singleton
    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions) : RequestManager = Glide
            .with(application)
            .setDefaultRequestOptions(requestOptions)

    @Singleton
    @Provides
    fun provideLogo(application: Application) : Drawable = ContextCompat.getDrawable(application, R.drawable.logo)!!
}