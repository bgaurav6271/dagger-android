package com.example.daggerandroid.di

import androidx.lifecycle.ViewModelProvider
import com.example.daggerandroid.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory)
    : ViewModelProvider.Factory
}