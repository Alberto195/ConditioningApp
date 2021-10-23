package com.example.conditioning511.data.core.storage

interface UserStorageSharedPreference {
    fun getUser(): Boolean

    fun setUser(init: Boolean)
}
