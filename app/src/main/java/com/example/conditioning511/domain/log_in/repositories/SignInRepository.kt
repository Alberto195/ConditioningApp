package com.example.conditioning511.domain.log_in.repositories

import androidx.compose.runtime.Composable

interface SignInRepository {
    @Composable
    fun signIn()

    fun signOut()
}