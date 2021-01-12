package com.example.daggerandroid.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.daggerandroid.R
import com.example.daggerandroid.extentions.showToast
import com.example.daggerandroid.models.UserItem
import com.example.daggerandroid.ui.auth.AuthResource
import com.example.daggerandroid.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {
    companion object{
        private const val TAG = "ProfileFragment"
    }

    private lateinit var viewModel: ProfileViewModel
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView: inside Profile Fragment")
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ProfileFragment was created")

        /**
         * We could have also got the sessionManager data directly from MainActivity, but that breaks
         * the MVVM architecture
         */
        viewModel = ViewModelProvider(this, providerFactory).get(ProfileViewModel::class.java)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        //Removing old observers is important. Fragments have there own lifecycles, if you don't remove old
        //Observers, you might have a bunch of observers floating around in memory
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner){ authResource ->
            when(authResource){
                /**
                 * Breaking the Interface Segregation Principle Of SOLID. Fix this
                 */
                is AuthResource.Authenticated -> setUserDetails(authResource.data)
                is AuthResource.Error -> setErrorDetails(authResource.message)
                is AuthResource.Loading -> {}
                is AuthResource.NotAuthenticated -> {}
            }

        }
    }

    private fun setErrorDetails(message: String?) {
        email.text = message
        username.text = "Error"
        website.text = "Error"
    }

    private fun setUserDetails(data: UserItem?) {
        email.text = data?.email
        username.text = data?.username
        website.text = data?.website
    }
}