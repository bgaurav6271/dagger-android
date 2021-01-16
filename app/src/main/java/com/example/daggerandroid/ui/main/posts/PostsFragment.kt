package com.example.daggerandroid.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerandroid.R
import com.example.daggerandroid.ui.main.Resource
import com.example.daggerandroid.utils.VerticalSpacingItemDecoration
import com.example.daggerandroid.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_posts.*
import javax.inject.Inject
import javax.inject.Provider

class PostsFragment : DaggerFragment() {
    companion object{
        private const val TAG = "PostsFragment"
    }

    @Inject
    lateinit var layoutManager: Provider<LinearLayoutManager>

    @Inject
    lateinit var itemDecoration: VerticalSpacingItemDecoration

    @Inject
    lateinit var adapter: PostsRecyclerAdapter

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: PostsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: Fragment created")
        viewModel = ViewModelProvider(this, providerFactory).get(PostsViewModel::class.java)
        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(viewLifecycleOwner){ listResource ->
            if(listResource != null){
                when(listResource){
                    is Resource.Success -> {
                        Log.d(TAG, "subscribeObservers: got posts")
                        adapter.setPosts(listResource.data!!)
                    }
                    is Resource.Error -> Log.e(TAG, "subscribeObservers: error ... ${listResource.message}")
                    is Resource.Loading -> Log.d(TAG, "subscribeObservers: Loading....")
                }
            }

        }
    }

    private fun initRecyclerView(){
        recyclerView.apply {
            layoutManager = this@PostsFragment.layoutManager.get()
            addItemDecoration(this@PostsFragment.itemDecoration)
            adapter = this@PostsFragment.adapter
        }

    }
}