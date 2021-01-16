package com.example.daggerandroid.ui.main.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerandroid.SessionManager
import com.example.daggerandroid.models.Post
import com.example.daggerandroid.network.MainApi
import com.example.daggerandroid.ui.main.Resource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(private val sessionManager: SessionManager,
    private val mainApi: MainApi): ViewModel() {
    companion object{
        private const val TAG = "PostsViewModel"
    }

    private var posts : MediatorLiveData<Resource<List<Post>>> = MediatorLiveData()

    fun observePosts(): LiveData<Resource<List<Post>>>{
        posts.value = Resource.Loading()
        val source :  LiveData<Resource<List<Post>>> = LiveDataReactiveStreams.fromPublisher(
                mainApi.getPostsFromUser(sessionManager.getAuthUser().value?.data?.id.toString())
                        .onErrorReturn(){
                            val post = Post(id = -1)
                            val posts = mutableListOf<Post>()
                            posts.add(post)
                            return@onErrorReturn posts
                        }
                        .map(Function { posts ->
                            if (posts.isNotEmpty()){
                                if(posts[0].id == -1) return@Function Resource.Error(
                                        "Something went Wrong", null) as Resource<List<Post>>
                            }
                            return@Function Resource.Success(posts)
                        }).subscribeOn(Schedulers.io())

        )
        posts.addSource(source){
            posts.value = it
            posts.removeSource(source)
        }
        return posts
    }


}