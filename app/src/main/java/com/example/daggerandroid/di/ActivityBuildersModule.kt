package com.example.daggerandroid.di

import com.example.daggerandroid.di.auth.AuthModule
import com.example.daggerandroid.di.auth.AuthViewModelsModule
import com.example.daggerandroid.ui.AuthActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    /**
     * Creates a subcomponent called AuthActivitySubComponent wth AuthViewModelsModule
     * and AuthModule as its Modules
     */
    @ContributesAndroidInjector(
        modules = [AuthViewModelsModule::class, AuthModule::class]
    )
    abstract fun contributeAuthActivity() : AuthActivity
}