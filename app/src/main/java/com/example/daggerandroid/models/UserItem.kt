package com.example.daggerandroid.models

data class UserItem(
    val address: Address? = null,
    val company: Company ? = null,
    val email: String? = null,
    val id: Int? = -1,
    val name: String? = null,
    val phone: String? = null,
    val username: String? = null,
    val website: String? = null
)