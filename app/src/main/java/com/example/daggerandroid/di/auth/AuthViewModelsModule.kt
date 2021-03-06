package com.example.daggerandroid.di.auth

import androidx.lifecycle.ViewModel
import com.example.daggerandroid.di.ViewModelKey
import com.example.daggerandroid.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel) : ViewModel
}