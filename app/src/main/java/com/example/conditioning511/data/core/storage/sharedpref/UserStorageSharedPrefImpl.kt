package com.example.conditioning511.data.core.storage.sharedpref

import android.content.Context
import com.example.conditioning511.data.core.storage.UserStorageSharedPreference
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserStorageSharedPrefImpl @Inject constructor(
    context: Context
) : UserStorageSharedPreference {
    private val mPreferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)

    override fun getUser(): Boolean {
        return mPreferences.getBoolean(INIT_USER, false)
    }

    override fun setUser(init: Boolean) {
        mPreferences.edit()
            .putBoolean(INIT_USER, init)
            .apply()
    }

    companion object {
        private const val INIT_USER = "initUser"
        private const val NAME_PREF = "preference"
    }

}