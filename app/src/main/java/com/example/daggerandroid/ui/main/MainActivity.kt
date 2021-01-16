package com.example.daggerandroid.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.daggerandroid.BaseActivity
import com.example.daggerandroid.R
import com.example.daggerandroid.extentions.showToast
import com.example.daggerandroid.ui.main.posts.PostsFragment
import com.example.daggerandroid.ui.main.profile.ProfileFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object{
        private const val TAG = "MainActivity"
    }

    private val navController : NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: Entered Main Activity")
        init()
    }

    private fun init(){
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        NavigationUI.setupWithNavController(navigationView, navController)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout -> {
                Log.d(TAG, "logging out")
                sessionManager.logOut()
                return true
            }

            android.R.id.home -> {
                if(drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START)
                    return true
                }
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_profile -> {
                val navOptions =  NavOptions.Builder()
                    .setPopUpTo(R.id.nav_graph, true).build()
                navController.navigate(R.id.profileScreen, null, navOptions)
            }
            R.id.nav_posts -> {
                if(isValidDestination(R.id.postsScreen)) navController.navigate(R.id.postsScreen)
            }
        }
        item.isChecked = true
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun isValidDestination(destination : Int) : Boolean{
        return destination != navController.currentDestination?.id
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawer_layout)
    }
}