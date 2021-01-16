package com.example.daggerandroid.di.main

import androidx.lifecycle.ViewModel
import com.example.daggerandroid.di.ViewModelKey
import com.example.daggerandroid.ui.main.posts.PostsViewModel
import com.example.daggerandroid.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(viewModel: PostsViewModel) : ViewModel
}