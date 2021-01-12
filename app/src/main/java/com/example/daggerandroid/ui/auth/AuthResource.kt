package com.example.daggerandroid.ui.auth

/**
 * Exposing network status using a Resource class that encapsulates both the data and its state
 */
sealed class AuthResource<T> (
    val data : T? = null,
    val message : String? = null
){
    class Authenticated<T>(data: T) : AuthResource<T>(data)
    class Error<T>(message: String, data: T? = null) : AuthResource<T>(data, message)
    class Loading<T>(data: T? = null) : AuthResource<T>(data)
    class NotAuthenticated<T>(data: T? = null, message: String? = null) : AuthResource<T>(data, message)
}