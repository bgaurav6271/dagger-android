package com.example.daggerandroid

import android.view.View

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun View.invisisble(){
    this.visibility = View.INVISIBLE
}