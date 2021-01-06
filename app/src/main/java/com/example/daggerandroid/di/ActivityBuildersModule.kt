package com.example.daggerandroid.di

import com.example.daggerandroid.ui.AuthActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeAuthActivity() : AuthActivity

    @Module
    companion object{
        @JvmStatic
        @Provides
        fun provideString() : String{
            return "This is a test String"
        }
    }
}